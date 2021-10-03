# Spring Data Overview

## Why

*Different databases have different data access libraries. Using Spring data our code will look much more similar.*

A lot of supported libraries:
- JPA
- MongoDB
- Cassandra
- ...

Abstractions:
- CRUD
- Derived queries
- Transactions
- Paging and Sorting
- Auditing
- ...

## Benefits

- Consistent programming model
  - Same abstractions for all modules
  - Rarely touch underlying libraries
  - Easy to learn additional modules
- Reduction in Boilerplate
- Integrates easily into Spring Boot

## Module Hierarchy

![Module Hierarchy](module-hierarchy.png)

## Spring Data Commons

### CRUD Repository

Marker interface:
```java
public interface CustomerRepository extends CrudRepository<Customer, Long> { }
```

### Deriving Queries

Method signature:
```java
List<Customer> findByName(String name);
```
Derived query:
```java
entityManager.createNativeQuery(
    "SELECT * FROM customers WHERE name = 'Steven' = ?")
      .setParameters(1, origin)
      .getResultList();
```

Benefits:
- No boilerplate
  - Generated for us
  - Executed for us
  - Mapped for us
- Easy learning curve
  - No need to write native query
  - Easy to swap implementations

## Paging & Sorting

```java
Page<Customer> findByName(String name, Pageable pageable, Sort sort);
```

## Dependencies For SQL Implementations

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
</dependency>
```

## Custom Implementations

*For custom implementation we need a separate interface.*

We can extend our custom interface in the Spring Data Interface and write our own implementation of the custom interface.
To write the implementation we can use the EntityManager.

## Transactions

**Atomicity**:

*Transactions consist of multiple queries in one unit, that either all get executed or none will.*

- Atomic

Example:
```java
Session sess = factory.openSession();
Transaction tx;
try {
    tx = sess.beginTransaction();
    bookTicket(ticket, payment);
    tx.commit();
}
catch (Exception e) {
    if (tx != null) {
        tx.rollback();
    }
    throw e;
}
finally {
    sess.close();
}
```

With annotation:

```java
@Transactional
public void bookTicket(Booking booking) {
    allocateSeat(booking.getSeat());
    makePayment(booking.getCardDetails());
}
```
Benefits:
- No more boilerplate
- Declarative and non-invasive
- Bugs are less likely
- Data-store agnostic

*When using a non-transactional db like Casandra, this will do nothing.*