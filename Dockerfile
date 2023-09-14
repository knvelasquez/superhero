FROM maven:3.8.3-openjdk-17-slim

#Author: Kevin Velasquez
LABEL MAINTAINER="Kevin Velásquez"

#App name
ARG APP_NAME=superhero

#Set working directory
WORKDIR /${APP_NAME}

#Copy sources from to container
COPY . /${APP_NAME}

#Build with maven
RUN mvn clean test package -X

CMD ["java", "-jar", "target/superhero-2.0.jar"] #Run with Spring Boot