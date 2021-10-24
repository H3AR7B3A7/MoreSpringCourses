# Spring Data & MongoDB

## MongoRepository Interface

- Interface: Repository
- Interface: CrudRepository
- Interface: PagingAndSortingRepository
- Interface: MongoRepository
- Interface: MyCustomRepository (Our own abstraction using IoC)

## @Query

Example:
```java
@Query("{'aircraft.nbSeats' : {$gte: ?0}}")
List<FlightInformation> findByMinAircraftNbSeats(int i);
```

## Mongo Document References

In mongo, denormalized data in a single document is optimal.
We should always try to minimize relationships between documents.

Relating documents:
- Manual: Saving the '_id' of a document in another document
- DBRefs: Link documents together by using the '_id' field, collection name and database name
  - Can link documents across collections or different databases
  - To resolve DBRefs, the application must perform additional queries
  - Cascading is not supported

DBRef formats:
- $ref
- $id
- $db

Example:
```json
{
  "_id": ObjectId("37291b08-9591-4db7-912a-c7ed37cc7ed3"),
  "name": "SomeName",
  "cap": 666,
  "engine": {
    "$ref": "engines",
    "$id": ObjectId("73535742-5097-4a14-ac93-c4a623c5d395"),
    "$db": "atm"   // Optional
  }
}
```
*The order of the formats matters!!! DBRefs are not 'smart'.*

Implementation:
```java
@Document
public class Aircraft {
    @Id
    private String id;
    private String name;
    private Integer cap;
    @DBRef
    private Engine engine;
}
```

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


---
Project under construction...