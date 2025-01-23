#!/bin/bash
cd backend
mvn clean install -DskipTests=true
cd geofer-presentation
mvn spring-boot:run -DskipTests=true -Dspring-boot.run.jvmArguments="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005"