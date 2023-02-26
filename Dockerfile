FROM openjdk:17-slim-buster

LABEL MAINTAINER="Kevin Velásquez"

WORKDIR /workspace

EXPOSE 8080

#ENTRYPOINT ["java","-jar","/workspace/target/superhero-2.0.jar"]