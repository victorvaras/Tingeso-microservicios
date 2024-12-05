FROM openjdk:17
ARG JAR_FILE=target/cliente-service.jar
COPY ${JAR_FILE} cliente-service.jar
ENTRYPOINT ["java","-jar","/cliente-service.jar"]