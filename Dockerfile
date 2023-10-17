FROM eclipse-temurin:17.0.6_10-jre
RUN mkdir -p /opt/dutybot
WORKDIR /opt/dutybot
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]