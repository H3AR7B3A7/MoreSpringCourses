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

## Query Execution

- Without indexes
  - Collection scan, each document is evaluated
  - Slow searches
  - Fast inserts & updates
- With indexes
  - Does not scan every document in collection
  - Fast searches
  - Slow inserts & updates

## Filter Operators

- is / ne (is, not equals)
- lt / lte (less than, less tha, equals)
- gt / gte (greater than, greater than equals)
- in (value in list)
- exists (has value)
- regex (fits pattern)