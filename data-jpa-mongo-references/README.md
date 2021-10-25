# Spring Data & MongoDB

## Mongo Lifecycle Events

- onBeforeConvert
- onBeforeSave
- onBeforeDelete
- onAfterConvert
- onAfterSave
- onAfterLoad
- onAfterDelete

Behavior:
- Lifecycle events are emitted only for root level types.
  Sub-documents are not subject to event publication unless they are annotated with @DBRef
- It is all happening in an async fashion. We have no guarantees to when an event is processed

Use-cases:
- Implementing cascade on save
- Trigger some job/action in different systems
- Security audits

Implementation:

- *Create a bean that **Extends** AbstractMongoEventListener and **Overrides** the methods.*
- *Create an event listener per feature. (Cascading/Auditing/...)*

## Hierarchy of Preference for References

1. No relationships - NoSQL philosophy
2. Use @DBRef, but only if we implement cascade on save, and we don't have bidirectional references
    - Population of related documents done by the framework
    - Cascading
3. Manual references

## Migrations

*Data changes over time.*

Mongo Documents vs Java Objects:
- A change in a Mongo Document will impact serialization / deserialization process
- A change in Mongo Document will impact the queries
- Changes to collections will also impact queries

Implementation options:
- Manual migrations using scripts (to be avoided at all cost)
- Create migration component ourselves
  - Version detection (& automatic upgrade)
  - Simple configuration
  - Logging
- Use an existing migration framework
  - Pro
    - Saves time
    - Increased focus on the application domain
  - Con
    - Not fully under our control

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