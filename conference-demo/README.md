# Spring Framework:
## Creating Your First Spring Boot Application

- [Spring Initializr](https://start.spring.io/)
- [IntelliJ IDEA](https://www.jetbrains.com/idea/download/#section=windows) (IDE)
- [Spring Boot CLI](https://docs.spring.io/spring-boot/docs/current/reference/html/getting-started.html#getting-started.installing.cli)

## Postgres DB Setup

We can find more information [here](https://github.com/dlbunker/ps-first-spring-boot-app/tree/master/database/postgresql).

## Configuration

- External sources
  - Command line parameters
  - JNDI
  - OS environment variables
- Internal sources
  - Servlet parameters
  - Property files
  - Java configuration

Order of precedence:
1. Command line parameters
2. SPRING_APPLICATION_JSON args
3. Servlet Parameters
4. JNDI
5. Java System Properties
6. OS environment variables
7. Profile properties
8. Application properties
9. @PropertySource annotations
10. Default properties

*We should pick two sources. One will set the defaults, and one to overwrite the default to keep configuration in one of 2 places.*

## Run Maven with Environment Variables

Since Maven is not aware of environment variables, we need to provide them in the command line.
> maven -Dname=value clean install

## Common Application Properties

We can find common Spring properties [here](https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html).

## Custom Auto Configuration

Example:
```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyAutoConfiguration {
  @Bean
  public someObject myMethod(){
    // ...
  }
}
```

Declaration in resources/META-INF/spring.factories to register autoconfiguration:

*org.springframework.boot.autoconfiguration.EnableAutoConfiguration=\com.mypackage.MyAutoConfiguration*

**Annotations**:
- @ConditionalOnClass
- @ConditionalOnMissingClass
- @ConditionalOnBean
- @ConditionalOnMissingBean
- @ConditionalOnProperty
- @ConditionalOnResource