FROM openjdk:11
COPY /target/PoopKings-0.0.1-SNAPSHOT.jar my-maven-docker-project.jar
ENTRYPOINT ["java", "-jar","my-maven-docker-project.jar"]