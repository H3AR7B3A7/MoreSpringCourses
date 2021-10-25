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