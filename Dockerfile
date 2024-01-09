FROM openjdk:17.0.2-jdk-slim

COPY target/student-0.0.1-SNAPSHOT.jar student-0.0.1-SNAPSHOT.jar

EXPOSE 3000

# Execute the application with the built jar
ENTRYPOINT ["java", "-jar", "student-0.0.1-SNAPSHOT.jar"]
