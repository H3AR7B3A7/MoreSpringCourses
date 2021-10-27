# Spring Security
Spring Security is a powerful and highly customizable authentication and access-control framework for Java applications. It is the de-facto standard for securing [[Spring]]-based applications.

Advantages:
- Application layer security, not tied to a specific web-server
- Easily extended to meet custom requirements
- Loosely couped, easily replacable when needed
- Good track record securing applications since 2003
- Maintained by a large open source community
- Allows focus on business features

*Due to adoption of security frameworks, vulnerabilities like CSRF (Cross-Site Request Forgery) & XSS(Cross-Site Scripting) are no longer on the OWASP top10.*

## Out of the Box Protection
- Session Fixation
- Clickjacking
- Cross Site Request Forgery
- Cross-site scripting

Where:
- In the browser
- Server-side
- Method & domain objects

## Terminology
- **Authentication**: The process or action of verifying the identity of a user or process
- **Authenticated Principal**: The logged in user / service or machine, stored in the session with the authentication object
- **Cookie**:Sent to the users browser by the webserver after authentication, to be included in subsequent requests to the server by the browser

- **Authorization**: The function of specifying access rights and priviliges to resources
- **Priviledges**: What an authenticated principal can do and/or access

## Authentication Strategies
- Basic and Form authentication
- Digest
- X.509 Certificates
- Oauth2 and OpenID Connect
- LDAP (Lightweight Directory Access Protocol)
- JWT (Json Web Tokens)

## Projects
- spring-security-core
- spring-security-config
- spring-security-test
- spring-security-web
- spring-security-oauth
- spring-security-ldap

## Spring Security & Boot
Dependency:
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

*By default on startup, a user is created named: 'user' and the password is printed in the logs.*

### Default configurtation
[[Spring Boot]] will autoconfigure needed beans, but will automatically back of when we configure our own. To accomplish this, among others, it leverages these annotations:
- @ConditionalOnMissingBean
- @ConditionalOnBean
- ...

## Architecture
Spring Security adds a layer of filters in the servlet container before the DispatcherServlet:
![Security Architecture](security-architecture.png)

### Filter Chains
![Security Filter Chain](security-filter-chain.png)

**Authentication Filters**:
- UsernamePasswordAuthenticationFilter
- DigestAuthenticationFilter
- BasicAuthenticationFilter
- OAuth2LoginAuthenticationFilter
- ...

*These will create an Authentication object.*

**Authentication objects**:
- UsernamePasswordAuthenticationToken
- OpenIDAuthenticationToken
- ...

*This token will be passed to the AuthenticationManager, which will delegate it to the appropriate Authentication Provider.*

**Authentication providers**:
- OpenIDAuthenticationProvider
- DaoAuthenticationProvider
- LdapAuthenticationProvider
- ...

*The provider will return an Authenticated Principal Object, for the Authentication Filter to store in the Security Context, where it can be retrieved by other filter lower down the filter chain.*