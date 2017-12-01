# x-ray-spring-boot-app

## Prerequisites

### Java SE Development Kit 8 (not Java 9!)
http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html and set JAVA_HOME after installation

### Gradle 4.2 and above
https://gradle.org/ and set GRADLE_HOME after installation

### Eclipse IDE - For Java Developers 
Start Eclipse *After* setting both JAVA_HOME, GRADLE_HOME and installing Lombok jar

## Build project

From the root project directory

``` bash
$ gradle clean assemble
```

## Start x-ray-spring-boot-app

``` bash
$ java -jar build/libs/x-ray-spring-boot-app.jar
```
