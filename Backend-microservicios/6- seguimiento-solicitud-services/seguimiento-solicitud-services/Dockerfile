FROM openjdk:17
ARG JAR_FILE=target/seguimiento-solicitud-services.jar
COPY ${JAR_FILE} seguimiento-solicitud-services
ENTRYPOINT ["java","-jar","/seguimiento-solicitud-services.jar"]