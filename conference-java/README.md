# Fundamentals Java

Based on "Spring Framework: Spring Fundamentals", a Pluralsight course by Bryan Hansen.

## Problems Spring Addresses
- JEE Blueprints
- WORA (write once, run anywhere)
- Hardcoded

## Java Configuration

- AppConfig
  - @Configuration
  - @Bean    
  - Setter & Constructor injection

## Scopes
- Singleton
- Prototype
- Request
- Session
- GlobalSession

```java
@Scope(value = BeanDefinition.SCOPE_SINGLETON)
```

## Autowiring

- @Autowired

- Stereotypes
  - @Component
  - @Repository
  - @Service
  - @Controller
  - ...