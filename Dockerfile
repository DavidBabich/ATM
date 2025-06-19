
FROM eclipse-temurin:17-jdk


WORKDIR /app


RUN apt-get update && apt-get install -y maven



COPY pom.xml .



RUN mvn dependency:go-offline -B



COPY src ./src

RUN mvn package -DskipTests



RUN ls -la target/



CMD ["java", "-jar", "target/atm-0.0.1-SNAPSHOT.jar"]