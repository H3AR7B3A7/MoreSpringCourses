# Spring Boot Actuator

Spring Boot includes a number of additional features to help you monitor and manage your application
when you push it to production. You can choose to manage and monitor your application by using
HTTP endpoints or with JMX. Auditing, health, and metrics gathering can also be automatically
applied to your application.

## Actuator

An actuator is a component responsible for controlling a mechanism or system.
It is triggered by a signal and generates an output.

Spring Boot Actuator lets us monitor and interact, or control, our application.
It comes with a number of features built-in by default:

[Production Ready Features](https://docs.spring.io/spring-boot/docs/current/reference/html/actuator.html#actuator.endpoints)

## Dependency

```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

## Examples

- Built-in health endpoint:

  http://localhost:8080/actuator/health

  To check if the application is healthy

- Built-in metrics endpoint:

  http://localhost:8080/metrics/jvm.memory.used

  To check the amount of memory the application is using in production

- Built-in configuration endpoint:

  http://localhost:8080/actuator/configprops

  To check id the application is using the right configuration value in production

- Built-in loggers endpoint

  http://localhost:8080/actuator/loggers

  To check or change the loglevel of all the available and configured loggers for the application

- ...

## Enable / Disable Endpoints

By default, all actuator endpoints, except shutdown, are enabled.

```properties
management.endpoint.<NAME>.enabled=false
```

We can however change this default behaviour:

```properties
management.endpoint.enabled-by-default=false
```

This way we only have to enable what we want to be enabled if we only want a few endpoints.

## Expose Endpoints

This makes the endpoints available for consumption.
- HTTP
- JMX

All endpoints are exposed via JMX by default.
Only the health and info endpoints are exposed via HTTP by default.

**Use caution when exposing endpoints, particularly via HTTP. Ensure the endpoints are secured.**

Example configurations:
```properties
## management.endpoint.http.expose.include=metrics
management.endpoint.http.expose.include=*
management.endpoint.jmx.expose.exclude=beans,metrics
```

*Remember to use quotes for "&ast;" in yaml files.*

## Securing Endpoints

To secure our endpoints we can add Spring Security to the project.

Dependency:
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

We can change the default username ('user') and password:
```properties
spring.security.user.name=<username>
spring.security.user.password=<password>
```

Endpoint security is completely customizable.

Example:
```java
@Configuration(proxyBeanMethods = false)
public class ActuatorSecurity extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpsSecurity http) throws Exception {
        http
                .requestMatcher(EndpointRequest.toAnyEnpoint())
                .authorizeRequests((req) -> req.anyRequest().hasRole("ADMIN"));
        http
                .httpBasics();
    }
}
```

## Health

To show more details of the health endpoint:

```properties
management.endpoint.health.show-details=always
```

### Custom Health Checks

Example
```java
@Component
public class MaxMemoryHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        boolean invalid = Runtime.getRuntime().maxMemory() < (100 * 1024 * 1024);
        Status status = invalid ? Status.DOWN : Status.UP;
        return Health.status(status).build();
    }
}
```

## Info

### Custom InfoContributor

Example:
```java
@Component
public class ProjectInfoContributor implements InfoContributor {
    @Override
    public void contribute(Info.Builder builder) {
        builder.withDetail("project_name", "...")
                .withDetail("owned_by_team", "...")
                .withDetail("point_of_contact", "...");
    }
}
```

## Metrics

### Custom Metrics

Example:
```java
import org.springframework.stereotype.Service;

@Service
public class ComplexService {
    private Timer timer;
    
    public ComplexService(MeterRegistry registry) {
        // Timer name
        timer = registry.timer("long.running.op.timer");
    }
    
    public void longRunningOperation() {
        timer.record(() -> {
            // A long-running operation
        });
    }
}
```

## Custom Actuator Endpoint

Examples:

```java
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;

@Component
@Endpoint(id = "container")
public class DockerEndpoint {

  @ReadOperation
  public String foo() {
    // Gather and return information (e.g. get info about the docker container)
  }

  @WriteOperation
  public void bar() {
    // Do some action (e.g. restart the docker container)
  }
}
```

```java
@Component
@Endpoint(id = "readiness")
public class ReadinessEndpoint {

    private String ready = "NOT_READY";

    @ReadOperation
    public String getReadiness() {
        return ready;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void setReady() {
        ready = "READY";
    }
}
```