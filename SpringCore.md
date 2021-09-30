# Spring

- Microservices
- Reactive
- Cloud
- Web apps
- Event Driven
- Batch

*J2EE Design and Development by Rod Johnson, early 2000*

## What is Spring

- Open Source
- Lightweight
- DI Container
    - Give a componbent what is needed, instead of making the component create it.
    - Objects do not have to worry about finding / connecting to each other.
- Framework

## Key principals:

- Don't repeat yourself
- Separation of concerns
- Convention over configuration
- Testability

## Conferences

- Spring One
- Spring.io

## Scopes

- Singleton
- Prototytpe
- Session
- Request
- Application (Spring 4.0)
- Global (< Spring 5)
- Web socket
- Refresh
- Thread
- Custom

## Injection

- Constructor
- Setter
- Field*

**[Why field injection is evil](https://odrotbohm.de/2013/11/why-field-injection-is-evil/)*


## AOP

Issue of adviced method within adviced method:

```
@Service
public class TransactionHandler {

@Transactional(propagation = Propagation.REQUIRES_NEW)
public <T> T executeInNewTransaction(Supplier<T> supplier) {
  return supplier.get();
}
```

https://stackoverflow.com/a/5251930/15452628

```
transactionHandler.runInNewTransaction(() -> {
  draftRepository.deleteByIds(List.of(entity.getId()));
  outboxService.createDraftDeletedEvent(payload);
});
```

https://stackoverflow.com/a/56327004/15452628
https://stackoverflow.com/a/48626789/15452628


### Pattern

[Modifiers] ReturnType [ClassType] MethodName (Arguments) [throws ExceptionType]

## Testing

[Mocks aren't Stubs](https://www.martinfowler.com/articles/mocksArentStubs.html)

## Jdbc Template

Template patterns:
- JdbcTemplate - JDBC
- JmsTemplate - JMS messages
- RestTemplate - REST
- WebServiceTemplate - SOAP
- ...

## ORM: JPA / Hibernate

*More overhead, but easyer to work with.*

- Entity ...

## Dynamic querries

https://docs.spring.io/spring-data/jpa/docs/current/api/org/springframework/data/jpa/domain/Specification.html

https://dimitr.im/writing-dynamic-queries-with-spring-data-jpa#using-specifications

https://docs.spring.io/spring-data/jdbc/docs/2.2.5/reference/html/#reference