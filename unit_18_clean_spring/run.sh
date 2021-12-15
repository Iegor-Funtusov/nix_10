#!/bin/sh

sh ./apache-tomcat-9.0.55/bin/shutdown.sh
rm -rf ./apache-tomcat-9.0.55/webapps/*
mvn clean install
cp target/cleanspring.war ./apache-tomcat-9.0.55/webapps
sh ./apache-tomcat-9.0.55/bin/startup.sh
