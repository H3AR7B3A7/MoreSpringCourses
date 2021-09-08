# AOP

Aspect Oriented Programming is used to separate business logic from cross-cutting functionality.

## Common Use Cases

- Logging
- User right check
- Translations
- Exception handling
- Caching
- Tweaking a legacy application
- ...

## Implementations

- Regular classes
- AspectJ

## Annotations

- @Before
- @Around
- @After
- @AfterReturning
- @AfterThrowing

## AOP Terms

- Target object: the object receiving advice by one or more aspects
- Introduction: Introduces additional fields or methods for a given class (additional interfaces)
- AOP Proxy: Object created by the AOP framework, to add aspects in execution
  - JDK Dynamic Proxy: Standard mechanism of proxy creation
  - CGLib: Code generation library proxy
- Weaving: The operation of program transformation that applies the aspect to the target object