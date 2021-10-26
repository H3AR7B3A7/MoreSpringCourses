# Spring Data & MongoDB

## Migrations

*Data changes over time.*

Mongo Documents vs Java Objects:
- A change in a Mongo Document will impact serialization / deserialization process
- A change in Mongo Document will impact the queries
- Changes to collections will also impact queries

Implementation options:
- Manual migrations using scripts (to be avoided at all cost)
- Create migration component ourselves
    - Needs
      - Version detection (& automatic upgrade)
      - Simple configuration
      - Logging
    - Pro
      - Complete control
      - Can meet every aspect of our needs
    - Con
      - Requires more time to develop
      - Is not trivial to create
- Use an existing migration framework
    - Pro
        - Saves time
        - Increased focus on the application domain
    - Con
        - The data migration process is not fully under our control
        - Some applications need more features than frameworks offer


### Mongobee

Dependency:
```xml
<dependency>
  <groupId>com.github.mongobee</groupId>
  <artifactId>mongobee</artifactId>
</dependency>
```
Configure bean:
```java
@Bean
public Mongobee mongobee() {
    Mongobee runner = new Mongobee(mongoUri);
    runner.setEnabled(true);
    runner.setChangeLogsScanPackage("yourdomain.*");
    runner.setMongoTemplate(this.mongoTemplate);
    return runner;
}
```
Changelog:
```java
@ChangeLog(order = "001")
public class DBChangeLog001 {
    @ChangeSet(order = "001", id = "seedCrew", author = "Steven")
    public void seedCrewMembers(MongoTemplate mongoTemplate) {
        // ...
    }
}
```

We can go [here](https://github.com/mongobee/mongobee) for more information and examples.

### Other Options

- Liquibase MongoDB Extension
    - [Example project](https://github.com/alexandru-slobodcicov/liquibase-nosql-quickstart)
- Mongock
    - [Example project](https://github.com/cloudyrock/mongock-integration-tests/tree/master/mongock-spring-v5/mongock-spring5-springdata3-it)