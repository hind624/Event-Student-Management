FROM openjdk:17.0.2-jdk-slim
RUN rm -rf /usr/local/tomcat/webapps/*
COPY target/student-0.0.1-SNAPSHOT.jar /usr/local/tomcat/webapps/ROOT.jar
EXPOSE 8080
CMD ["catalina.sh", "run"]
