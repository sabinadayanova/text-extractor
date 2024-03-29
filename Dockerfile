#
# Build stage
#
FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean validate compile package

#
# Package stage
#
FROM openjdk:11-jre-slim
COPY --from=build /home/app/target/text-extractor-1.0-SNAPSHOT-jar-with-dependencies.jar text-extractor-1.0-SNAPSHOT-jar-with-dependencies.jar
ENTRYPOINT ["java","-jar","text-extractor-1.0-SNAPSHOT-jar-with-dependencies.jar"]