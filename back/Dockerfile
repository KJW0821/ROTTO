FROM openjdk:17-jdk
WORKDIR /app
COPY build/libs/rotto-0.0.1-SNAPSHOT.jar deployBE.jar
EXPOSE 8080

CMD ["java", "-jar", "app.jar"]