FROM alpine

WORKDIR /app
RUN apk update
RUN apk add openjdk17
RUN apk add maven
COPY . .
RUN mvn clean install -DskipTests
RUN cp target/*.jar app.jar
CMD java -jar app.jar