# Spring Data & MongoDB

## MongoDB Concepts

*Mongo BSON (= Binary JSON):*

- Database
- Collections (<> Tables)
- Documents (<> Rows)
- Fields (<> Columns)
- Id (<> Primary Key)

## Dependency

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-mongodb</artifactId>
</dependency>
```

## Annotations

- @Document
- @Id
- @Field
- @Transient - Not persisted
- @Indexed - To speed up lookups
- @TextIndexed
- @CompoundIndex
- @DbRef - To join tables

## Properties

- Mongo server IP
- Mongo server port
- Database name
- Credentials