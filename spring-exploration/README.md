# Spring Exploration

## Base Dependency

```xml
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-context</artifactId>
</dependency>
```

## Spring context

```java
ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
ctx.getBeanDefinitionNames();
```

Minimal beans:
- internalConfigurationAnnotationProcessor
- internalAutowiredAnnotationProcessor
- internalEventListenerProcessor
- internalEventListenerFactory
- springConfig

## Resources

- No prefix
- classpath:
- file:
- http:

## Annotations

- @Bean
- @ComponentScan
- @PropertySource
- @Value
- @Component
  - @Configuration
  - @Service
  - @Repository
- @Autowired

## Environment

Interface representing the environment in which the current application is running.
Models two key aspects of the application environment: profiles and properties.
Methods related to property access are exposed via the PropertyResolver superinterface.