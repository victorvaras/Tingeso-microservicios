FROM openjdk:17
ARG JAR_FILE=target/geteway-service.jar
COPY ${JAR_FILE} geteway-service
ENTRYPOINT ["java","-jar","/geteway-service.jar"]