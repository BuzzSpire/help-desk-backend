# this file is used to build the docker image
# to build the image run the following command
# docker build -t helpdesk .
# note: this docer file is used to build the image for the helpdesk application

FROM openjdk:20-jdk-slim
COPY target/helpdesk-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar", "-p", "8080"]