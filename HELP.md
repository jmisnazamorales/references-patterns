# References pattern application

This is a batch application, it will run every X seconds to read, write and process page to find a pattern

## Version 1

* Built on Java 8 using Spring framework.
* Uses local file storage
* Uses a cron expression to run every X seconds, depends on you want.

## Assumptions

This application was created using:

* Java 8
* Maven 3
* Spring Boot
* Windows 10 Pro

## Prerequisites

1) Install Java, follow this [link](https://www.digitalocean.com/community/tutorials/how-to-install-java-with-apt-on-ubuntu-18-04).
2) Install Maven, follow this [link](https://linuxize.com/post/how-to-install-apache-maven-on-ubuntu-18-04/).
3) Install Git, follow this [link](https://www.liquidweb.com/kb/install-git-ubuntu-16-04-lts/)

## Instructions

1) Configure these environment variables:

|Environment variable|Example|Description|
|-|-|-|
|`DIRECTORY_INPUT`|Directory where the user decide put the files to process the pages|
|`DIRECTORY_OUTPUT`|Directory where the task will put the files from processed pages|
|`DIRECTORY_FAILURE`|Directory where the task will put the files with problems|
|`PATTERN_CRON`|*/60 * * * * *|Runs every 60 seconds. This is the cron by default|

2) Go to `pattern application` folder and execute:

```pattern application
mvn clean install
java -jar ./target/references-patterns-1.0.jar
```

### Cron expressions

Examples:

|Expression|Description|
|-|-|
|`*/30 * * * * *`|Runs every 30 seconds|
|`*/60 * * * * *`|Runs every 60 seconds|
|`0 0,4,8,12,16,20,24,28,32,36,40,44,48,52,56 * * * *`|Runs every 4 minutes, starting at minute **0**|
|`0 1,5,9,13,17,21,25,29,33,37,41,45,49,53,57 * * * *`|Runs every 4 minutes, starting at minute **1**|
|`0 2,6,10,14,18,22,26,30,34,38,42,46,50,54,58 * * * *`|Runs every 4 minutes, starting at minute **2**|
|`0 3,7,11,15,19,23,27,31,35,39,43,47,51,55,59 * * * *`|Runs every 4 minutes, starting at minute **3**|
