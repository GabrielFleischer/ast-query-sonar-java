package org.sonar.plugins.java.api.query

sealed class Droppable<out T> {
  data class Keep<T>(val data: T) : Droppable<T>()
  data object Drop : Droppable<Nothing>()
}
