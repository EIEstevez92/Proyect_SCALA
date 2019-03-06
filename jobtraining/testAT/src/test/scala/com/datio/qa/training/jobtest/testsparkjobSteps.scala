package com.datio.qa.training

import com.datio.integration.utils.exec.RunSpark
import com.datio.integration.utils.read.KafkaMetricReader
import cucumber.api.scala.{EN, ScalaDsl}
import org.apache.avro.generic.GenericRecord
import org.scalatest.Matchers

class testsparkjobSteps extends Matchers with ScalaDsl with EN {

  var configFilePathHDFS = ""
  var exit_code: Int = _
  var messageFromKafka: GenericRecord = _

  Given("""^a config file path in HDFS (.*)$""") {
    (configFilePathHDFS: String) =>

      this.configFilePathHDFS = configFilePathHDFS
  }

  When("""^execute (.*) in Spark$""") {
    (mainClass: String) =>

      exit_code = RunSpark.exec(configFilePathHDFS, s"com.datio.qa.training.${mainClass}")
      println("Exit code: " + exit_code)
  }

  Then("""^should send metrics to Kafka with key (.*)""") {
    (key: String) =>

      val topic = "topic-metrics-testsparkjob"
      messageFromKafka = KafkaMetricReader.read(topic, key)

  }

  And("""^should have (\d+) lines written and (\d+) lines readed""") {
    (linesWriten: Long, linesReaded: Long) =>

      messageFromKafka.get("recordsWritten").asInstanceOf[Long] shouldBe linesWriten
      messageFromKafka.get("recordsRead").asInstanceOf[Long] shouldBe linesReaded
  }

}

