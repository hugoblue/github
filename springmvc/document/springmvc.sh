#!/bin/ksh
echo "--------helloWorld------->>"
JAVA_HOME="/usr/java/jdk1.8.0_121"
JAVA_OPTIONS=" -hotspot -Xms128m -Xmx512m "
CLASS_PATH=.;./lib;
java -jar /usr/deploy/springmvc_jar/springmvc.jar