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

## Insert Process

Persisting a Document
- Create collection if it doesn't exist
- Generate ID if not present
- Save single document atomically

**Single Document transactions**:

*An operation on a single document is atomic. Because relationships are embedded in a single document,
this mostly eliminates the need for multi-document transactions.*

**Multi-Document transactions**:

*For situations that require atomicity to multiple documents (in single or multiple collections),
Mongo 4+ supports multi document transactions. However, we should avoid this where possible.*

- Insert has batch operation support. (e.g.: insertAll(List<>))

## Save / Update

*Save can also insert, but we should use it only to update.*

Save:
- Save completely overwrites the original document if the id already exists, never just one field.
- Save does **not** have batch operation support.

Update:
- Update only affects the fields defined in the update definition, not the whole document.
- Update has batch operation support.

## Delete

Deleting documents:
- Single
- Multi
- All

## Mongo Converter

*Is a feature used for mapping all Java types to/from DBObjects when storing or retrieving these objects.*

Custom converter:
- Create write converter
- Create read converter
- Register converters as a Spring bean

## MongoRepository Interface

- Interface: Repository
- Interface: CrudRepository
- Interface: PagingAndSortingRepository
- Interface: MongoRepository
- Interface: MyCustomRepository (Our own abstraction using IoC)



---
Project under construction...