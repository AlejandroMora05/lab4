FROM maven:3.9.5-eclipse-temurin-21 AS build

COPY pom.xml /app/

COPY src /app/src/

WORKDIR /app

RUN mvn clean package -DskipTests



# Use the official OpenJDK image to run the app

FROM eclipse-temurin:21-jre-jammy

COPY --from=build /app/target/lab4-0.0.1-SNAPSHOT.jar /usr/local/lib/lab4.jar

EXPOSE 8088

ENTRYPOINT ["java", "-jar", "/usr/local/lib/lab4.jar"]