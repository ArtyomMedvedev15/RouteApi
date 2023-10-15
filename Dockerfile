FROM maven:3.9.2-eclipse-temurin-17-alpine as builder
COPY ./src src/
COPY ./pom.xml pom.xml
RUN mvn clean package -DskipTests
ENV API_KEY=$API_KEY

FROM eclipse-temurin:17-jre-alpine
COPY --from=builder target/*.war routeapi-0.1.war
EXPOSE 8080
CMD ["java","-jar","-DopenrouteserviceApiKey=$API_KEY}","routeapi-0.1.war"]