import json
import os, subprocess
import urllib.request
from pathlib import Path


def run(command, env):
  process = subprocess.Popen(command, env=env, stdout=subprocess.PIPE, stderr=subprocess.STDOUT, shell=True)

  while process.poll() is None:
    line = process.stdout.readline()
    if line:
      try:
        yield "[RUN] " + line.decode('utf-8')
      except UnicodeDecodeError:
        yield "[WARN] Decoding error"


def run_perf_for_plugin(plugin=None):
  """
  Run performance tests for a given plugin

  If none is provided, run performance tests for the currently built plugin (in target/)
  """
  env = os.environ.copy()

  env['JAVA_HOME'] = "C:\\Program Files\\Amazon Corretto\\jdk17.0.13_11"

  for path in run(f"mvn test -Pit-perf -DPERF_PLUGIN_PATH={plugin}".split(), env):
    print(path)


def get_latest_release(output_path):
  """
  Get the latest release of the SonarJava plugin
  """
  ...


def get_metrics_from_perf_results():
  """
  Get metrics from the performance tests results
  """

  # load the results in json format located in target/performance/sonar.java.performance.measure.json
  with open('./target/performance/sonar.java.performance.measure.json') as f:
    d = json.load(f)
    return d


def move_perf_metrics(run, jar):
  """
  Move the ./target/performance directory to ./results/
  """
  os.rename("./target/performance", "./results/run" + str(run) + "/" + jar)

def reset_perf_metrics():
  """
  Reset the ./target/performance directory
  """
  if os.path.exists("./target/performance"):
    for file in os.listdir("./target/performance"):
      os.remove(os.path.join("./target/performance", file))

def merge_results(original, current):
  if isinstance(original, dict) and isinstance(current, dict):

    merged = {}

    for key in original.keys():
      if key not in current:
        merged[key] = original[key]
      elif key == "calls":
        merged["calls_original"] = original[key]
        merged["calls_current"] = current[key]
      elif key == "durationNanos":
        merged["delta"] = 1 - (float(current[key]) / original[key])
      else:
        merged[key] = merge_results(original[key], current[key])

    for key in current.keys():
      if key not in original:
        merged[key] = current[key]

    return merged
  elif isinstance(original, list) and isinstance(current, list):
    merged = []

    for i in range(len(original)):
      merged.append(merge_results(original[i], current[i]))

    return merged
  elif original == current:
    return original
  else:
    raise ValueError("The two values are different")


def main():
  # get the latest release of the SonarJava plugin
  # get_latest_release("run/latest_release.jar")

  for run in [1, 2, 3, 4, 5]:
      for jar in [
        "baseline",
        "fw-raw",
        "fw-merge",
        "fw-flatten",
        "fw-all"
      ]:
        print(f"Running performance tests for {jar}")
        reset_perf_metrics()
        run_perf_for_plugin("run/" + jar + ".jar")
        results = get_metrics_from_perf_results()
        move_perf_metrics(run, jar)
        print(results)

  # run performance tests for the latest release

  # run performance tests for the current build
  #run_perf_for_plugin()
  #current = get_metrics_from_perf_results()
#
  ## compare the results
  #merged = merge_results(original, current)
#
  #with open('results.json', 'w') as fp:
  #  json.dump(merged, fp)


if __name__ == "__main__":
  main()
