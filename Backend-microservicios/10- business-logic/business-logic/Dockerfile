FROM openjdk:17
ARG JAR_FILE=target/business_logic.jar
COPY ${JAR_FILE} business_logic
ENTRYPOINT ["java","-jar","/business_logic.jar"]