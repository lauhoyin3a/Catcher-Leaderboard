FROM openjdk:17
ADD target/springboot_docker.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]