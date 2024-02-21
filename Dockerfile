FROM eclipse-temurin:17.0.10_7-jre-jammy
VOLUME /tmp
RUN mvn clean install
ARG JAR_FILE=/target/PoopKings-0.0.2-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar /app.jar ${0} ${@}"]
