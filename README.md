# A POC upon RSocket interaction models Stream/Channel
A [RSocket](https://rsocket.io/docs/Protocol) protocol stream POC in order to evaluate async/reactive services communications

## Technology
Based on RSocket SPI from [Spring Framework](https://docs.spring.io/spring-integration/reference/html/rsocket.html#rsocket)


### Server
The server is in charge to receive the request from client and stream out based on interval of one second.

**To start the server prompt from command line**
```bash
@ cd server
$ ./mvnw clean package spring-boot:run -DskipTests=true
```

### Client
The client is based on Spring Shell App that is able to start/stop the consumer

**To start the client prompt from command line**

```bash
@ cd client
$ ./mvnw clean package spring-boot:run -DskipTests=true
```

## Goal
Measure the latency between services communications as well as security and resource consumption when using channel/stream interaction models.