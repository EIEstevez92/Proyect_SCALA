package com.datio.qa.training

import cucumber.api.CucumberOptions
import cucumber.api.junit.Cucumber
import org.junit.runner.RunWith
import org.junit.{AfterClass, BeforeClass}

import scala.sys.process._

object RunCukesTest {

  @BeforeClass
  def startHDFS(): Unit = {

    val scriptStartPath = ClassLoader.getSystemResource("docker/start.sh").getPath

    "chmod +x " + scriptStartPath !

    scriptStartPath !
  }

  @AfterClass
  def stopHDFS(): Unit = {

    val scriptStopPath = ClassLoader.getSystemResource("docker/stop.sh").getPath

    "chmod +x " + scriptStopPath !

    scriptStopPath !
  }

}

@RunWith(classOf[Cucumber])
@CucumberOptions(features = Array(
  //"src/test/resources/acceptanceTests/testsparkjob.feature"
),
  tags = Array("~@ignore")
)
class RunCukesTest
