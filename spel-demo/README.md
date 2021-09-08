# SpEL (Java)

An expression language can be used for accessing data that is stored in objects or beans.
This makes it possible to perform dynamic bean wiring in applications.

- Write expression as a string
- Evaluated at runtime
- Inject beans/values in other beans

## Uses

SpEL is generally used to:
- Dependency inject existing bean into another object
- Dependency inject a bean based on environment condition
- Access and manipulate object graphs at runtime

## Syntax

```
"#{experession}"
```

- Literal String expression
  
  ```
  "'Hello World'"
  ```

- Accession a variable

  ```
  "#greeting"
  ```

- Method call

  ```
  "#greeting.length()"
  ```

- Mathematical operation

  ```
  "#greeting.length()*10"
  ```
  
- Relational operator

  ```
  "#greeting.lenth()>10"
  ```

- Logical operator

  ```
  "#greeting.length()>10 and #greeting.length()<20"
  ```
  
## Sources

- **Literals**
- **Object Fields**

  We can access the fields of objects at runtime.

  ```java
  StandardEvaluationContext ec1 = new StandardEvaluationContext();
  ec1.setVariable("greeting","Hello USA");
  String msg = (String) parser.parseExpression("#greeting").getValue(ec1);
  ```

- **Environment Variables**

  SpEL provides pre-defined systemProperties variable.

  > -Duser.country=BE

  ```java
  StandardEvaluationContext propsContext = new StandardEvaluationContext();
  propsContext.setVariable("systemProperties",System.getProperties());
  Expression expCountry = parser.parseExpression("#systemProperties['user.country']");
  parser.parseExpression("country").setValue(userContext,expCountry.getValue(propsContext));
  ```

*The latter 2 allow us to make the application behave conditionally using key/value pairs set at runtime.*

We can find more examples [here](https://www.baeldung.com/spring-expression-language).

## Annotation: @Value

Used to specify a default value, which can be expressed using SpEL.

Used in:
- Fields
- Methods
- Constructor params