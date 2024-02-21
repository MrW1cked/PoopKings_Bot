FROM maven:3.6.0-jdk-11-slim AS build
COPY src src
COPY pom.xml app
RUN pom.xml clean package

#
# PACKAGE STAGE
#
FROM openjdk:11-jre-slim
COPY --from=build target/PoopKings-0.0.2-SNAPSHOT.jar PoopKings-0.0.2-SNAPSHOT.jar
CMD ["java","-jar","PoopKings-0.0.2-SNAPSHOT.jar"]