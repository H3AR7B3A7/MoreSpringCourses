# Spring Data With Mongo

## Dependencies

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-mongodb</artifactId>
</dependency>
<dependency>
    <groupId>de.flapdoodle.embed</groupId>
    <artifactId>de.flapdoodle.embed.mongo</artifactId>
</dependency>
```

## String

Mongo prefers strings as data ype, so we change our id from Long to String.

## LocalDateTime

Mongodb removed the nanosecond information from Date type and has a precision of milliseconds.

We can truncate LocalDateTime in Java:

```java
Clock millisecondClock = Clock.tick(Clock.systemDefaultZone(), Duration.ofNanos(1000000)); 
LocalDateTime d = LocalDateTime.now(millisecondClock);
```