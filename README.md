# micro-services-app
This is a Spring Boot Applicaiton with an in built Tomcat Server. Multiple micro apps connect to the same DB.

## Spring Gateway Service
- Routes / Paths can be configured in the .yml file in the gaterway service for any different micro services

## Spring Discovery Service (Eureka)
- Healthcheck service

## Spring Config Server (for relatime config updates)
- Externalizing the application config properties

## Spring Open Feign 
- Connection from one service to service thru HTTP (Open Feign)
- Declarative REST Client: Feign creates a dynamic implementation of an interface decorated with JAX-RS or Spring MVC annotations

## Spring Data JPA
- Using Hibernate : a JPA Implementation

## Tracing between micro services
- Uses [Micrometer](https://micrometer.io/docs/tracing) for Tracing
- Uses [ZipKin Server](https://zipkin.io/) to log TraceId
- Uses [feign-micrometer](https://mvnrepository.com/artifact/io.github.openfeign/feign-micrometer) for passing TraceId between micro services

## Spring for GraphQL

## All Services required before starting the app
### Docker service
- [Zipkin Docker](https://hub.docker.com/r/openzipkin/zipkin)
- Run this [docker_compose](https://github.com/Nishanthk3/Mysql-Debezium-connector) file for DB 
