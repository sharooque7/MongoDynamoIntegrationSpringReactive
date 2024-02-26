# Use an official JDK 17 slim image as the base image
FROM openjdk:17-jdk-slim

LABEL authors="sharooque"

# Set environment variables
ENV JAVA_HOME=/usr/local/openjdk-17
ENV PATH=$JAVA_HOME/bin:$PATH

# Set the working directory in the container
WORKDIR /usr/src/app

EXPOSE 8080

# Copy the application JAR file into the container
COPY target/spring-reactive-mongo-and-dynamo-0.0.1-SNAPSHOT.jar app.jar

# Specify the command to run your application
CMD ["java", "-jar", "app.jar"]
