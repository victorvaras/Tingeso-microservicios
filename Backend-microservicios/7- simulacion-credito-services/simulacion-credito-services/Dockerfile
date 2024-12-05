FROM openjdk:17
ARG JAR_FILE=target/simulacion-credito-services.jar
COPY ${JAR_FILE} simulacion-credito-services.jar
ENTRYPOINT ["java","-jar","/simulacion-credito-services.jar"]