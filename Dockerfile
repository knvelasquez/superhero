FROM maven:3.8.3-openjdk-17-slim

#Author: Kevin Velasquez
LABEL MAINTAINER="Kevin Velásquez"

#App name
ARG APP_NAME=superhero

#Set working directory
WORKDIR /${APP_NAME}

#Copy sources from to container
COPY . /${APP_NAME}

COPY /lib/FilterLibrary-1.0.0.jar /${APP_NAME}/lib/FilterLibrary-1.0.0.jar
RUN mvn install:install-file -Dfile=/${APP_NAME}/lib/FilterLibrary-1.0.0.jar -DgroupId=com.lab -DartifactId=FilterLibrary -Dversion=1.0.0 -Dpackaging=jar

COPY /lib/JwtLibrary-1.0.0.jar /${APP_NAME}/lib/JwtLibrary-1.0.0.jar
RUN mvn install:install-file -Dfile=/${APP_NAME}/lib/JwtLibrary-1.0.0.jar -DgroupId=com.jwtlibrary -DartifactId=JwtLibrary -Dversion=1.0.0 -Dpackaging=jar

#Build with maven
RUN mvn clean test package -X

CMD ["java", "-jar", "target/superhero-3.0.jar"] #Run with Spring Boot