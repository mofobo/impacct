FROM openjdk:11
LABEL maintainer="mohammed.fonseca@gmail.com"
VOLUME /main-app
ADD build/libs/impacct-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar","/app.jar"]