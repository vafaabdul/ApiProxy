FROM maven:3.6.1-jdk-8-alpine AS MAVEN_BUILD
# copy the pom and src code to the container
COPY ./ ./
# package our application code
RUN mvn clean package
# the second stage of our build will use open jdk 8 on alpine 3.9
FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY --from=MAVEN_BUILD ${JAR_FILE} apiproxy.jar
ENTRYPOINT ["java","-jar","/apiproxy.jar"]
EXPOSE 8080