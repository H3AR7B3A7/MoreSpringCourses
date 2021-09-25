# Documenting Spring Data

## Why

- Users did not write the API
- Answer questions
- Know what is available and how to use
- Helps your API get used
- Identify changes when updated

## Swagger

*Unmaintained documentation is worse than no documentation at all.*

Swagger is generated from source code and thus always up to date:
- Less errors
- Quick an Easy

[Swagger-UI](localhost:8080/swagger-ui/#/)

## Open API Specification

Open API Specification is a standard for RESTful APIs.

[Json description of the API](localhost:8080/v2/api-docs)

## Swagger UI

Displays API information in a human readable format.
It also provides a rest client to use and test the API.

We can find more tools for API design, testing and deployment [here](http://swagger.io).

## Springfox

Library:
- Generates Swagger from Spring Project
- Uses Spring and Swagger annotations
- Good default values for documentation

## Dependency

```xml
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-boot-starter</artifactId>
    <version>3.0.0</version>
</dependency>
```

## Configuration

```java
@Configuration
@EnableSwagger2WebMvc
@Import(SpringDataRestConfiguration.class)
public class ApplicationSwaggerConfig {

  @Bean
  public Docket employeeApi() {
    return new Docket(DocumentationType.SWAGGER_2)
	  .select()
	  .apis(RequestHandlerSelectors.any())
	  .paths(PathSelectors.any())
	  .build();
  }
}
```