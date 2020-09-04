FROM openjdk:8-alpine
RUN apk --no-cache add curl
COPY ./SOAPWebService1.0.jar /usr/app/
WORKDIR /usr/app
EXPOSE 8080
ENTRYPOINT ["java", "-cp", "SOAPWebService1.0.jar", "it.sapienza.softeng.soapws.Server"]