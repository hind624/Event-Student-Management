FROM openjdk:17-alpine
WORKDIR /app



COPY target/student-0.0.1-SNAPSHOT.jar /app/student-0.0.1-SNAPSHOT.jar


# Execute the application with the built jar
CMD ["java", "-jar", "student-0.0.1-SNAPSHOT.jar"]
EXPOSE 3000
