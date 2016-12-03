#!/bin/bash
./gradlew clean build war
java -Xdebug -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005 \
     -jar jetty/jetty-runner.jar --port 8080 --path angularSpring as-web/build/libs/angularSpring-1.0-SNAPSHOT.war