FROM openjdk:17
ARG JAR_FILE=target/solicitud-credito-services.jar
COPY ${JAR_FILE} solicitud-credito-services
ENTRYPOINT ["java","-jar","/solicitud-credito-services.jar"]