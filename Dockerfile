FROM maven:3.9.6-eclipse-temurin-17
COPY src src
COPY pom.xml app
# PACKAGE STAGE
#
FROM maven:3.9.6-eclipse-temurin-17
COPY target/PoopKings-0.0.2-SNAPSHOT.jar PoopKings-0.0.2-SNAPSHOT.jar
CMD ["java","-jar","PoopKings-0.0.2-SNAPSHOT.jar"]