FROM openjdk:17
ARG JAR_FILE=target/tipo-prestamo-services.jar
COPY ${JAR_FILE} tipo-prestamo-services.jar
ENTRYPOINT ["java","-jar","/tipo-prestamo-services.jar"]