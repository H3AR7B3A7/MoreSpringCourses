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

## Bean Lifecycle

- Instantiation
- Populate Properties
- BeanNameAware
- BeanFactoryAware
- Pre Initialization - BeanPostProcessors
- InitializeBean
- initMethod
- Post Initialization - BeanPostProcessors

## BeanAware Annotations

- @PostConstruct
- @PreDestroy

```xml
<dependency>
    <groupId>javax.annotation</groupId>
    <artifactId>javax.annotation-api</artifactId>
    <version>1.3.2</version>
</dependency>
```

## Factory Bean Configuration

- Builds on initMethod concept
- Factory Method Pattern
- Legacy Code
- Contract without Constructor
- Static Methods

## SpEL

Spring Expression Language is used for:
- Manipulate Object Graph
- Evaluate at Runtime
- Configuration

## Spring AOP Proxies

Spring utilizes proxies.

```java
ProxyFactory factory = new ProxyFactory(new SimplePojo());
factory.addInterface(Pojo.class);
factory.addAvice(new RetryAdvice());
factory.setExposeProxy(true);

Pojo pojo = (Pojo) factory.getProxy();

// This is a method call on the proxy
pojo.foo();
```

## Bean Profiles

- Adapt Environments
- Runtime Configuration