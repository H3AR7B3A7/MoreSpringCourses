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