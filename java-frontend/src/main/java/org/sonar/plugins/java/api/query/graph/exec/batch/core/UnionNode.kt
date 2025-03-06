package org.sonar.plugins.java.api.query.graph.exec.batch.core

import org.sonar.plugins.java.api.query.graph.NodeId
import org.sonar.plugins.java.api.query.graph.exec.ExecutionContext
import org.sonar.plugins.java.api.query.graph.exec.Store
import org.sonar.plugins.java.api.query.graph.exec.batch.BatchNode
import org.sonar.plugins.java.api.query.graph.exec.batch.ChildNode
import org.sonar.plugins.java.api.query.graph.exec.batch.MergeSignalHelper
import org.sonar.plugins.java.api.query.graph.exec.batch.Signal
import org.sonar.plugins.java.api.query.graph.visual.FlowType
import org.sonar.plugins.java.api.query.graph.visual.VisualInfo

// TODO Handle cases where scopes are generated by multiple parents but not all of them
// -> The solution is probably to allow unions of only two parents and create cascades of unions
class UnionNode<IN>(
  id: NodeId,
  children: List<ChildNode<IN>>,
  val parentCount: Int
) : BatchNode<IN, IN>(id, children) {

  override val isSink = false

  private val queues = Store { mutableMapOf<NodeId, List<Signal<IN>>>().withDefault { emptyList() } }

  override fun onValue(context: ExecutionContext, caller: NodeId, value: Signal.Value<IN>) {
    addToQueue(context, caller, value)
  }

  override fun onScopeEnd(context: ExecutionContext, caller: NodeId, signal: Signal.ScopeEnd) {
    addToQueue(context, caller, signal)

    processQueues(context, signal)
  }

  private fun processQueues(context: ExecutionContext, end: Signal.ScopeEnd) {
    val queueMap = queues.get(context)
    val queues = queueMap.entries.toList()

    if (queues.size < parentCount || queues.any { end !in it.value }) {
      return
    }

    val firstId = queues.first().key
    val rest = queues.drop(1)

    var firstRun = true
    var acc = queues.first().value

    for (queue in rest) {
      val nextAcc = mutableListOf<Signal<IN>>()

      val newQueues = MergeSignalHelper.processScope(
        acc,
        queue.value,
        end,
        { a, b, first ->
          nextAcc.add(
            if (first) a + b
            else b
          )
        },
        { signal -> nextAcc.add(signal) }
      )

      checkNotNull(newQueues) { "UnionNode should always have a result" }

      val (newAcc, newQueue) = newQueues

      queueMap[queue.key] = newQueue
      if (firstRun) {
        queueMap[firstId] = newAcc
        firstRun = false
      }

      acc = nextAcc
    }

    acc.forEach { propagate(context, it) }

  }

  private fun addToQueue(context: ExecutionContext, caller: NodeId, signal: Signal<IN>) {
    val qs = queues.get(context)
    val callerQueue = qs.getValue(caller) + signal
    qs.put(caller, callerQueue)
  }

  override fun getFlowType(parentsInfo: Map<BatchNode<*, *>, VisualInfo>) = FlowType.Many

  override fun toString() = "Union-$id"
}
