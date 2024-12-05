FROM openjdk:17
ARG JAR_FILE=target/business_logic.jar
COPY ${JAR_FILE} business_logic.jar
ENTRYPOINT ["java","-jar","/business_logic.jar"]