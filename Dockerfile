# Use an official Scala runtime as a parent image
FROM sbtscala/scala-sbt:eclipse-temurin-17.0.4_1.7.1_3.2.0
# Set the working directory to /app
WORKDIR /app
# Copy the current directory contents into the container at /app
COPY . /app
# Compile the Scala code
RUN sbt run
