# Spring Boot Fundamentals

## REST

Representational State Transfer:
- Data and functionality are considered resources
- Resources are manipulated using a fixed set of operations
- Resources can be represented in multiple formats
- Communication between the client and endpoint is stateless

## Spring MVC Annotations

- @RestController
  - @Controller
  - @ResponseBody
- @GetMapping
- ...

## ResponseEntity

Represents the entire http response:
- Status
  - 200: OK
  - 400: BAD_REQUEST
  - 409: CONFLICT
  - 404: NOT_FOUND
- Header
- Body

ResponseStatusException:
- Programmatic alternative to @ResponseStatus
- Provide HttpStatus and a reason and a cause
- Exceptions can be created programmatically
- Provides a default error mapping

## cURL

A tool to call api's from command line:

> curl http://localhost:8080/tza/applications