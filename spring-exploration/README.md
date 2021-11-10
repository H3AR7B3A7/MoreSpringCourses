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