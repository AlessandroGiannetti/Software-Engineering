FROM openjdk:8-alpine
RUN apk --no-cache add curl
COPY ./4-SOAP-WebService-1.0.jar /usr/app/
WORKDIR /usr/app
EXPOSE 8080
ENTRYPOINT ["java", "-cp", "4-SOAP-WebService-1.0.jar", "it.sapienza.softeng.soapws.Server"]