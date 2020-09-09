FROM openjdk:8-alpine
RUN apk --no-cache add curl
COPY ./2019-9-JMS-Server-Flights-1.0-SNAPSHOT-jar-with-dependencies.jar /usr/app/
WORKDIR /usr/app
ENTRYPOINT ["java", "-cp", "2019-9-JMS-Server-Flights-1.0-SNAPSHOT-jar-with-dependencies.jar", "it.sapienza.softeng.flights.Server"]