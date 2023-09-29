# micro-services-app
A Spring Boot application with an in-built Tomcat Server. Multiple micro apps connect to the same DB.

## Spring Gateway Service
- Routes / Paths can be configured in the .yml file in the gateway service for any different microservices

## Spring Discovery Service (Eureka)
- Healthcheck service

## Spring Config Server (for real-time config updates)
- Externalizing the application config properties

## Spring Open Feign 
- Connection from one service to service thru HTTP (Open Feign)
- Declarative REST Client: Feign creates a dynamic implementation of an interface decorated with JAX-RS or Spring MVC annotations

## Spring Data JPA
- Using Hibernate: a JPA Implementation

## Tracing between microservices
- Uses [Micrometer](https://micrometer.io/docs/tracing) for Tracing
- Uses [ZipKin Server](https://zipkin.io/) to log TraceId
- Uses [feign-micrometer](https://mvnrepository.com/artifact/io.github.openfeign/feign-micrometer) for passing TraceId between microservices

## Spring for GraphQL

## All Services required before starting the app
### Docker service
- [Zipkin Docker](https://hub.docker.com/r/openzipkin/zipkin)
- Run this [docker_compose](https://github.com/Nishanthk3/Mysql-Debezium-connector) file for DB
- Run this [docker-compose-dynamodb](https://github.com/Nishanthk3/aws-dynamodb) file for Dynamo DB
