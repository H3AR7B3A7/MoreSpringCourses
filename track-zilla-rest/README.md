# Spring Boot Fundamentals

## REST

Representational State Transfer:
- Data and functionality are considered resources
- Resources are manipulated using a fixed set of operations
- Resources can be represented in multiple formats
- Communication between the client and endpoint is stateless

## Spring MVC Annotations

- @RestController
  - @Controller
  - @ResponseBody
- @GetMapping
- ...

## ResponseEntity

Represents the entire http response:
- Status
  - 200: OK
  - 400: BAD_REQUEST
  - 409: CONFLICT
  - 404: NOT_FOUND
- Header
- Body

ResponseStatusException:
- Programmatic alternative to @ResponseStatus
- Provide HttpStatus and a reason and a cause
- Exceptions can be created programmatically
- Provides a default error mapping

## cURL

A tool to call api's from command line:

> curl http://localhost:8080/tza/applications

## Spring Boot Actuator

- Health checks and auditing
- Metrics and HTTP tracing
- Exposes HTTP or JMX endpoints

Dependency:
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

http://localhost:8080/actuator

We can configure the application to see more endpoints:

```properties
management.endpoints.web.exposure.include=info,health,metrics,loggers,beans,mappings
management.endpoint.health.show-details=always
```

We can create custom endpoints:
- Create a component class
- Implement HealthIndicator

## Testing

- Unit tests
- Integration tests

Dependency:
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>
```

- JUnit
- Mockito
- Spring Test (MockMVC)

@SpringBootTest:
- Convenient way to start up an application context to be used in a test

@WebMvcTest:
- Scans @Controller and @RestController
- Does NOT load the full application context
- Dependent beans must be mocked
- Speeds up testing by loading small portions of application

Mock environment:

*Springs @Mockbean annotation works well with mockito.*

TestRestTemplate:

```java
private TestRestTemplate restTemplate = new TestRestTemplate();
ResponseEntity<List> response = this.restTemplate.getForEntity(
        "http://localhost:" + port + "/tza/applications/", List.class);

assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
```