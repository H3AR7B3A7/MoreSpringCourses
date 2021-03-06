# Spring Boot Fundamentals

Overview:
- Features of Spring Boot
  - Spring Boot starters
  - Auto configuration
- Bootstrapping an application
  - Spring Initializr
- Data access
- Spring MVC

## Starter Dependency

Spring Boot requires only one dependency to get all the **basics** for web application development up and running:

```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

Includes:
- Spring MVC
- REST
- Tomcat
- Jackson

Spring boot will add most of the libraries needed for **testing** using also just one dependency:

```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-test</artifactId>
  <scope>test</scope>
</dependency>
```
Includes:
- JUnit
- Mockito
- Hamcrest
- Spring Core
- Spring Test

Another dependency will add everything required for Spring Data JPA with Hibernate:

```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
```
Includes:
- Hibernate
- Spring Data JPA
- Spring ORM

## Spring Boot CLI

- Command Line Interface
- Applications using Groovy scripts
- Rapid Prototyping

### Installation

#### Linux:
> curl -s "https://get.sdkman.io" | bash

> sdk install springboot

> spring --version

#### Windows:
- Go to this [link](https://docs.spring.io/spring-boot/docs/current/reference/html/getting-started.html#getting-started.installing.cli)
- Download and extract spring-boot-cli-2.5.4-bin.zip to a desired location
- Follow the 'INSTALL.txt'-instructions
  - Create a SPRING_HOME environment variable
  - Add %SPRING_HOME%/bin to the classpath
  - (Or just use the 'spring.bat'-file in the bin folder)

### Generating Projects

> spring init fundamentals2

> spring init --dependencies=web,data-jpa fundamentals3

### Running Groovy Scripts

> spring run app.groovy

## Actuator

- Monitor running application
- Manage via HTTP endpoints or JMX
- Health status, metrics, loggers, audit events, HTTP trace

## Bill of Materials (BOM)

The parent **pom.xml** holds pre-configured versions for a lot of dependencies, selected by the Spring team to work well together:

```xml
<parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.5.4</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
```

## Auto-Configuration

*Spring Boot takes a convention over configuration approach.*

- Finds JARs on the classpath and auto-configures bean
- DAta source for Hibernate or DispatcherServlet for Spring MVC
- User defined beans take precedence over defaults
- Auto-configuration is applied after user defined beans are registered

Insights:
- Start application with --debug switch
- Add a simple property to application.properties

  ```properties
  logging.level.org.springframework=DEBUG
  ```

- Use the Spring Boot Actuator

## Annotations

The **@SpringBootApplication** annotation:
- @SpringBootConfiguration

  Replaces @Configuration and annotates a class af configuration

- @EnableAutoConfiguration

  Tells Spring Boot to configure beans

- @ComponentScan
  
  Tells Spring Boot to scan current package and subpackages

## Properties

[Common Application Properties](https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html)

Different properties for multiple Spring Boot Profiles:
```
applications-{profile}.properties
```

## ORM with JPA

- Persistence Data Store
- Java Database Connectivity (**JDBC**)

  Connects to DB and executes queries

- Java Persistence API (**JPA**)

  Abstraction that makes it easy to map Java objects to relational DB (Doesn't 'DO' anything)

- **Hibernate** (Persistence Provider, Springs implementation for JPA)

  Provides repository support for the JPA

## MVC

- **Model**

  Representation of data in te system

- **View**

  Responsible for displaying data

- **Controller**

  Directing incoming user requests

## Thymeleaf

- Fragments
- Repeatable chunks of code
- Components reused across pages

## Spring Boot Maven Plugin

- Repackages our JAR / WAR files to be executed
- Runs Spring Boot Application
- Provides built-in dependency resolver
- Manages lifecycle of Spring Boot Application

## Packaging & Deployment

Options:
- Packaged as a traditional web application in a WAR file
- Standalone application, packaged in executable JAR file