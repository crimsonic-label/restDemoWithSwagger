# Spring REST demo project
Contain two modules: server and client with swagger.yaml

### Server Module
Is a Spring boot application with simple REST service
Swagger yaml file is generated during compile phase for the service stored in /generated/swagger.yaml

Plugin used to generate swagger.yaml:
```
<groupId>io.springfox</groupId>
<artifactId>springfox-swagger2</artifactId>
<version>2.9.2</version>
```

Swagger-ui api is also configured for the service:
```
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger2</artifactId>
    <version>2.9.2</version>
</dependency>
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger-ui</artifactId>
    <version>2.9.2</version>
</dependency>
```
Can be displayed:
* api: http://localhost:8080/v2/api-docs
* swagger-ui: http://localhost:8080/swagger-ui.html

### Client module
Service REST client is generated according to /generated/swagger.yaml with plugin:
```
<groupId>io.swagger</groupId>
<artifactId>swagger-codegen-maven-plugin</artifactId>
<version>2.4.5</version>
```

A test connects a server when is run with command:
```
mvn spring-boot:run
```
