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

## Form & Basic Authentication
By default Spring implements form authentication. It's a simple form creating a POST request to the /login endpoint, with the username and password in the body.

With basic authentication a concatenation of username:password is transmitted via the header. It is base64 encoded to encode non http-compatible characters. It doesn't add any extra security, because it is easily decoded.

>curl http://localhost:8080/endpoint -H "Authorization: Basic c3RldmVuOnBhc3N3b3Jk"

[Encode / Decode Base64](https://www.base64encode.org/)

### Overwriting Default Configuration
By default, SpringBootWebSecurityConfiguration configures both form and basic authentication. We can overwrite the defaults by creating our own implementation of WebSecurityConfigurerAdapter.

```java
@Configuration  
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {  
 @Override  
 protected void configure(HttpSecurity http) throws Exception {  
 http  
        .authorizeRequests()  
        .anyRequest()  
        .authenticated()  
        .and()  
        .httpBasic();  
    }   
}
```

## Multiple Filter Chains
The DelegatingFilterProxy will iterate through all the available filter chains. To do this it needs the order and the RequestMatcher of each FilterChain. The default order of the filter chain is '100' and 2 filter chains can't have the same order. 

## Default Protection
By default, Spring Security will include a lot of headers in responses to clients. These instruct the browser on how to communicate more securely with the application. They are added by the header filter, which we can also customize.

### Cache Control Header
By default, Spring tells the browser to block all caching to prevent identity theft.
When we do want caching on non-sensitive information, we can disable cachecontrol in the configuration or the controller:

```java
@Override  
protected void configure(HttpSecurity http) throws Exception {
	http.headers().cachecontrol().disable()
	.and()
	// ...
}
``` 
Or better:
```java
@GetMapping("/price")
public ResponseEntity<BigDecimal> priceOfBtc() {
	return ResponseEntity.ok()
		.cacheControl(CacheControl.maxAge(30, TimeUnit.SECONDS))
		.body(priceService.getCurrentPriceForCrypto("BTC"));
}
```