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
By default, Spring Security tells the browser to block all caching to prevent identity theft, using these headers:
```
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Pragme: no-cache
Expires: 0
```
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

### Reflected Cross-site Scripting (XSS)
*Reflected cross-site scripting occurs when an attacker injects browser executable code within a single HTTP response.*

Generally cross-site scripting protection is already enabled in the browser, but it doesn't hurt that Spring also includes this header just in case:

```
X-XSS-Protection: 1; mode=block 
```

### MIME Type Sniffing
*Content sniffing, also known as media type sniffing or MIME sniffing, is the practice of inspecting the content of a byte stream to attempt to deduce the file format of the data within it. Polyglot files, were files that could be interpreted as multiple file types (images / JavaScript) in an attempt to execute scripts in the browser, similar to XSS attacks.*

To prevent the client from trying to determine what the file type is, other than what is declared by the application, Spring Security includes this header:
```
X-Content-Type-Options: nosniff
```

### Cross-Site Request Forgery (CSRF)
*Cross-site request forgery is an attack that forces an end user to execute unwanted actions on a web application in which they're currently authenticated. Because the browser has a cookie stored it would identify malicious requests as coming from the authenticated user.*

Spring Security by default protects against CSRF by using the synchronizer token pattern. When a user logs in to the application the server responds with a cookie and a csrf-token. It then expects both the cookie and the token to be returned for all future requests. The token is application specific and not stored in the browser, so malicious sites have no access to it. it goes without saying that we should not disable the csrf security feature.

### Clickjacking
To disallow the browser to embed a page inside a frame, iframe or object Spring Security also includes this header:
```
X-Frame-Options: DENY
```
This is to prevent the user of being tricked into clicking on something in our web application without being aware of it.

We can configure this header to be less restrictive:
```java
http.headers().frameoptions().sameOrigin()
```

## Optional Protection
### Content Security Policy
Content security policies are a great way to protect against attacks like cross-site scripting, where compromised CDNs or user-generated content could embed malicious content on the user's page and be executed in their browser.

It uses header like this one:
```
content-security-policy: <directive> <source list> ; <directive> <source list>
```

These allow the web application to notify the users browser about the root sources of specific resources.

We can configure them like this:
```java
http.headers().contentSecurityPolicy("script-src: self")
```
or:
```java
http.headers().contentSecurityPolicy("script-src: http://mydomain...")
```

### Referrer Policy
This policy instructs the browser to include the origin of the request.
```java
http.headers().referrerPolicy().policy(ReferrerPolicy.ORIGIN)
```

## Http Firewall
Ways in which the HTTP firewall protects our application:
- Enforcing a white-list of HTTP methods
    - Against verb tampering and cross-site tracing attacks (connect & trace are blocked by default)
- Protection against URL tampering
    - Blocks (encoded) percentages, encoded periods / semicolons / forward slashes / double forwards & backslashes
    - Enforces only principle ASCII characters can be used

![Security Http Firewall](security-http-firewall.png)

Two implementations:
- StrictHttpFirewall
- DefaultHttpFirewall

*Contrary to what the name would have you think, Spring by default uses the StrictHttpFirewall implementation.*

We can also create our own bean of the HttpFirewall, for which Spring provides many setter methods to configure it.