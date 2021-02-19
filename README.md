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
The client support two kinds of iteration mode:
1. Spring command line using directly the terminal
2. REST Endpoints 

#### From command line
**To start the client prompt from command line**
```bash
@ cd client
$ ./mvnw clean package spring-boot:run -DskipTests=true
```
#### From Rest Endpoints
There are the following endpoints that will consume the provider RSocket to reach out the MongoDB

| Endpoint                                             | MediaType         | Interaction Mode |
|------------------------------------------------------|-------------------|------------------|
| http://localhost:8080/order/stream                   | text/event-stream | Stream           |
| http://localhost:8080/order/stream/monitor/{orderId} | text/event-stream | Stream           |
| http://localhost:8080/order/{orderId}                | application/json  | Request-Response |

### Docker support
The build can be done to use the built in (requires Docker) plugins that will
generate docker images, in order to use that prompt from terminal:
```bash
@ cd {project_moduel}
$ ./mvnw spring-boot:build-image -DskipTests=true
$ docker run -p 8080:8080 {image}
```

## Proto Buffer Support
Further details from [Protobuff](https://github.com/protocolbuffers/protobuf/tree/master)

The PB compiler can be download from [Maven Central](https://search.maven.org/classic/#search%7Cga%7C1%7Cg%3A"com.google.protobuf"%20AND%20a%3A"protoc") and have to copied to `${BASE_DIR_REPO}/server/src/main`

### First compile the proto file
From terminal execute

```bash
$ protc --java_out=java resources/Order.proto
```

## Goal
Measure the latency between services communications as well as security and resource consumption when using channel/stream interaction models.

## CI
Based on GitHub Actions towards client and server
