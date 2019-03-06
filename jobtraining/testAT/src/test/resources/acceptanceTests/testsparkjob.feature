@ActivateSegment
Feature: Feature for testsparkjob

  Scenario: Test testsparkjob should result OK
    Given a config file path in HDFS hdfs://hadoop:9000/testsparkjob.conf
    When execute testsparkjob in Spark
    Then should send metrics to Kafka with key testsparkjob-test
    And should have 0 lines written and 0 lines readed

  Scenario Outline: Test custom implementations result OK
    Given a config file path in HDFS hdfs://hadoop:9000/<pathConfig>.conf
    When execute testsparkjob in Spark
    Then should send metrics to Kafka with key testsparkjob-test
    And should have <linesWritten> lines written and <linesRead> lines readed

      Examples:
        | pathConfig     | linesWritten | linesRead |
        | testsparkjob | 0            | 0         |

