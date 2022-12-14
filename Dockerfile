FROM gradle:7.2.0-jdk17 as build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN ./gradlew clean build

FROM openjdk:17-jdk-alpine
EXPOSE 8080

COPY --from=build /home/gradle/src/build/libs/imdplay-0.0.1-SNAPSHOT.jar /app/imdplay.jar

ENTRYPOINT ["java","-jar","/app/imdplay.jar"]
