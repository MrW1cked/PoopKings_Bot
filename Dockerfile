FROM eclipse-temurin:17.0.10_7-jre-jammy
VOLUME /tmp
ARG JAR_FILE=target/PoopKings-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar /app2.jar ${0} ${@}"]
