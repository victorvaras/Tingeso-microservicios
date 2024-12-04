FROM openjdk:17
ARG JAR_FILE=target/evaluacion-credito-services.jar
COPY ${JAR_FILE} evaluacion-credito-services
ENTRYPOINT ["java","-jar","/evaluacion-credito-services.jar"]