# Thymeleaf

## Dependencies

Artifact thymeleaf is the Core library:
```xml
<dependency>
    <groupId>org.thymeleaf</groupId>
    <artifactId>thymeleaf</artifactId>
</dependency>
```

Artifact thymeleaf-spring5 (there is also 3 or 4) allows integrating Thymeleaf with the Spring Framework,
especially (but not only) Spring MVC:
```xml
<dependency>
    <groupId>org.thymeleaf</groupId>
    <artifactId>thymeleaf-spring5</artifactId>
</dependency>
```

If you use Spring Boot, you can just use the spring-boot-starter-thymeleaf dependency.
It already contains the above two dependencies as well as some others:
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
```