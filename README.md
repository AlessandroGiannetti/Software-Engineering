# JMS JAR execution

**RUN ActiveMQ**
1. cd /opt/activemq/apache-activemq-5.15.10/bin
2. sudo sh activemq start
3. password: biar

**Check ActiveMQ**
1. localhost:8161

**Execute JAR file**
1. java -cp \<file name with dependencies\> \<package\>.\<nameServer\>

# SOAP JAR execution

**Execute JAR file**
1. java -cp \<file name with dependencies\> \<package\>.\<nameServer\>


# Docker micro services
1. if use microservices put 0.0.0.0 on the server ip configuration.
## docker-compose.yml
```
version: '3'
services:
  soap:
    build: .
    ports:
      - "8080:8080"
```
port: "external exposed:internal server port"
## Dockerfile
```
FROM openjdk:8-alpine
RUN apk --no-cache add curl
COPY ./4-SOAP-WebService-1.0.jar /usr/app/
WORKDIR /usr/app
EXPOSE 8080  
ENTRYPOINT ["java", "-cp", "4-SOAP-WebService-1.0.jar", "it.sapienza.softeng.soapws.Server"]
```
**set up the container**
1. cd \<directory container\>
2. docker build -t \<name\> **.**
3. docker-compose up 

**show images on docker**
1. docker images

**show the container that are running:**
1. docker ps

# IF multiple SERVICES on multiple container 
look *docker/JSM* folder AND *03B-JMSServant-microService*.
we must modify the logical address like:
* props.setProperty(Context.PROVIDER_URL, "tcp://localhost:61616");
* props.setProperty(Context.PROVIDER_URL, "tcp://broker:61616");
and verify if the based service is running on the server file:
```
//check if activeMQ is up and running (micro service)
        boolean serverReady = false;

        while (!serverReady) {
            Socket socket = new Socket();
            try {
                socket.connect(new InetSocketAddress("broker", 8161), 5000);
                socket.close();
                serverReady = true;
                System.out.println("... broker is finally ready");
            } catch(Exception ex){
                serverReady = false;
                System.out.println("... broker NOT yet ready...");
            }
        }
```
