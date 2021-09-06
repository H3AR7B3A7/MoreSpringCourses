# Spring Boot Fundamentals

## GraphQL

*GraphQL offers greater flexibility in the response returned than REST.*

- Allows client to specify the exact data needed
- Aggregation of data from multiple sources
- No longer required to call multiple APIs for needed data
- GraphQL offers maximum efficiency and flexibility

### Example

Query:
```graphQL
{
  findAllApplications
  {
    id
    owner
  }
}
```

Response:
```json
{
  "data": {
    "findAllAplications": [
      {
        "id": "1",
        "owner": "Kesha"
      },
      {
        "id": "2",
        "owner": "Jane"
      }
    ]
  }
}
```

### Dependencies

```xml
<dependency>
    <groupId>com.graphql-java</groupId>
    <artifactId>graphql-spring-boot-starter</artifactId>
</dependency>
<dependency>
    <groupId>com.graphql-java</groupId>
    <artifactId>graphql-java-tools</artifactId>
</dependency>
```

### Schema

- Defines data points offered via an API
- Data types and relationships
- Operations available
- Graphql-java-tools parses schemas ending in *.graphqls*

### Graphqls

- There can only be one root Query
- There can only be one Mutation type

## GraphIQL

A useful tool to test mutations and queries against a GraphQL server.

```xml
<dependency>
    <groupId>com.graphql-java</groupId>
    <artifactId>graphiql-spring-boot-starter</artifactId>
</dependency>
```

http://localhost:8080/graphiql