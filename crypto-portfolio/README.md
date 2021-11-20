# Spring Security
Spring Security is a powerful and highly customizable authentication and access-control framework for Java applications.
It is the de-facto standard for securing [[Spring]]-based applications.

Advantages:
- Application layer security, not tied to a specific web-server
- Easily extended to meet custom requirements
- Loosely couped, easily replacable when needed
- Good track record securing applications since 2003
- Maintained by a large open source community
- Allows focus on business features

*Due to adoption of security frameworks, vulnerabilities like CSRF (Cross-Site Request Forgery) & XSS(Cross-Site Scripting)
are no longer on the OWASP top10.*

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
[[Spring Boot]] will autoconfigure needed beans, but will automatically back of when we configure our own.
To accomplish this, among others, it leverages these annotations:
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

*We can see all the filter chains configured by Spring by setting a breakpoint in the doFilter() method in FilterChainProxy.*
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

*The provider will return an Authenticated Principal Object, for the Authentication Filter to store in the Security Context,
where it can be retrieved by other filter lower down the filter chain.*

## Form & Basic Authentication
By default Spring implements form authentication. It's a simple form creating a POST request to the /login endpoint,
with the username and password in the body.

With basic authentication a concatenation of username:password is transmitted via the header. It is base64 encoded to
encode non http-compatible characters. It doesn't add any extra security, because it is easily decoded.

>curl http://localhost:8080/endpoint -H "Authorization: Basic c3RldmVuOnBhc3N3b3Jk"

[Encode / Decode Base64](https://www.base64encode.org/)

### Overwriting Default Configuration
By default, SpringBootWebSecurityConfiguration configures both form and basic authentication. We can overwrite the
defaults by creating our own implementation of WebSecurityConfigurerAdapter.

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
The DelegatingFilterProxy will iterate through all the available filter chains. To do this it needs the order and the
RequestMatcher of each FilterChain. The default order of the filter chain is '100' and 2 filter chains can't have the same order. 

## Default Protection
By default, Spring Security will include a lot of headers in responses to clients. These instruct the browser on how to
communicate more securely with the application. They are added by the header filter, which we can also customize.

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

Generally cross-site scripting protection is already enabled in the browser, but it doesn't hurt that Spring also
includes this header just in case:

```
X-XSS-Protection: 1; mode=block 
```

### MIME Type Sniffing
*Content sniffing, also known as media type sniffing or MIME sniffing, is the practice of inspecting the content of a
byte stream to attempt to deduce the file format of the data within it. Polyglot files, were files that could be
interpreted as multiple file types (images / JavaScript) in an attempt to execute scripts in the browser,
similar to XSS attacks.*

To prevent the client from trying to determine what the file type is, other than what is declared by the application,
Spring Security includes this header:
```
X-Content-Type-Options: nosniff
```

### Cross-Site Request Forgery (CSRF)
*Cross-site request forgery is an attack that forces an end user to execute unwanted actions on a web application in
which they're currently authenticated. Because the browser has a cookie stored it would identify malicious requests
as coming from the authenticated user.*

Spring Security by default protects against CSRF by using the synchronizer token pattern. When a user logs in to the
application the server responds with a cookie and a csrf-token. It then expects both the cookie and the token to be
returned for all future requests. The token is application specific and not stored in the browser, so malicious sites
have no access to it. it goes without saying that we should not disable the csrf security feature.

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
Content security policies are a great way to protect against attacks like cross-site scripting, where compromised CDNs
or user-generated content could embed malicious content on the user's page and be executed in their browser.

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

## HTTPS
*Http over Transport Layer Security (TLS)*

### Man-in-the-middle Attacks
Https prevents interception of the authentication request to steal the user's credentials, or the hijacking of the
user's session by malicious third parties as requests bounce between servers. Without https there is also no way to
confirm the destination server is who they claim to be.

### TLS & SSL
TLS is the protocol used by the client and server to initiate encrypted communication between themselves, and a way for
the client to confirm the identity of the server.

With TLS there is a handshake before any data is transmitted. The client and the server agree on the protocols and
algorithms they're going to use. Then the server sends its X509 certificate. It contains information about the server.

Secure Sockets Layer (SSL) was the predecessor of TLS.

### Self Signed Certificate
We can generate a self signed certificate, however in production we should only use a certificate from a certified authority.

To generate the certificate:
>keytool -genkey -alias tomcat -storetype PKCS12 -keyalg RSA -keysize 2048 -keystore keystore.p12  -validity 400

In project properties:
```properties
server.port=8443
server.ssl.key-store-password=pass
server.ssl.key-store=classpath:keystore.p12
server.ssl.key-store-type=PKCS12
server.ssl.key-alias=tomcat
```
*To be truly secure, we would not want this information to be in plain text inside our code. When the server is breached,
or the source code or release artifact get leaked this information would be up for grabs.*

We can now connect via https, but we will get a warning from the browser that it can not verify the server is who they say they are.

To redirect HTTP requests to port 80 we can send a redirect response to the user. Spring boot only allows to configure
one connector via the properties, which we now already use for HTTPS requests on port 443. We can create a bean by
accessing the TomcatServletWebServerFactory to help us out:

```java
@Bean
public ServletWebServerFactory servletContainer() {
	TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
		@Override
		protected void postProcessContext(Context context) {
			SecurityConstraint securityConstraint = new SecurityConstraint();
			securityConstraint.setUserConstraint("CONFIDENTIAL");
			SecurityCollection collection = new SecurityCollection();
			collection.addPattern("/*");
			securityConstraint.addCollection(collection);
			context.addConstraint(securityConstraint);
		}
	};
	tomcat.addAdditionalTomcatConnectors(redirectConnector());
	return tomcat;
}

private Connector redirectConnector() {
	Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
	connector.setScheme("http");
	connector.setPort(80);
	connector.setRedirectport(443);
	return connector;
}
```

### Http Strict-Transport-Security (HSTS) Header
The initial redirect request and response are still via HTTP, and thus still susceptible to a Man-in-the-middle attack.
A hacker can intercept the response from the server and inject malicious scripts or change the redirect to their ow site
by changing the header. This is even rather simple for them using tools like **Wifi Pineapple**.
Once a user has connected to some wifi network, their device will automatically try to reconnect through probe requests,
continuously looking for these networks. Pineapple can detect these probes and masquerade as the exact same SSID,
tricking the device into connecting to it. At this point the user is using the DNS server of the device and the hacker
can then use DNS spoofing. Most users would not notice they are not on a HTTPS secured web page and could share their
information with the hacker through a web interface looking exactly like one of their trusted websites.

To prevent this Spring Security again by default adds a HSTS Header:

```
Strict-Transport-Security: max-age=31536000; includeSubDomains
```

It informs the browser that our website requires HTTPS. The browser will cache this and use HTTPS for all subsequent requests.

### Free Trusted Certificate
With **Let's Encrypt** we can get a domain validation certificate for free, so users don't get the annoying warning and
can have the confidence that the server they are connecting to actually owns the domain.

### ACME
*Automated Certificate Management Environment protocol*

Used to verify that we control the domain address being used without any human intervention. We can do this by cloning
the Certbot from source on Git. The Certbot runs a small web server to communicate with Let's Encrypt to verify that
we control that server and own the domain. It generates and renews keys for us. We need to make sure that port 80 is
open for outbound and have Python 2.7 installed.

### Preloaded Hosts List
This form is used to submit domains for inclusion in Chrome's HTTP Strict Transport Security (HSTS) preload list.
This is a list of sites that are hard coded into Chrome as being HTTPS only. Most major browsers also have HSTS
preload lists based on the Chrome list.

This prevents event the initial HTTP request to our domain to be intercepted by malicious third parties.

## Single Page Web Applications
When Authenticating single page web applications we can use the a login page provided by the Spring application,
but more likely we will want to use the JavaScript framework to handle the login form.

We can configure HttpSecurity to either use Http Basic login or a Login Form:
- Basic: *username:password* in the header
- Form: *FormData* can hold more than just username and password in the body of a POST request

## Secrets

Examples of secrets:
- User credentials
- Database service credentials
- Keystore passwords
- API keys
- ...

*These are often, but should not be hard coded in the code, nor in the properties files.*

We should:
- Encrypting our secrets within the application
- Externalize our secrets with Spring Cloud Vault
- Follow good practices
  - Auditing
  - Rotation
  - Invalidation

### Encrypting
We can use the [Jasypt library](http://www.jasypt.org/) to encrypt our secrets, which has good Spring support.

Dependency:
```xml
<dependency>
	<groupId>com.github.ulisesbocchio</groupId>
	<artifactId>jasypt-spring-boot-starter</artifactId>
</dependency>
```

Jasypt will encrypt our secrets by using an algorithm and a password. We can use the **encrypt.bat** script to generate
the encrypted secret. Then we can provide the encrypted secret in the properties:

```properties
server.ssl.key-store-password=ENC(RRAFEAfAF5eaf75aeF7aGFEA)
```

We now only need to provide the algorithm and the password in a secure way.

```properties
jasypt.encryptor.password=AIDNIFGNEAZOFJ
jasypt.encryptor.iv-generator-classname=org.jasypt.iv.NoIvGenerator
jasypt.encryptor.algorithm=PBEWithMD5AndTripleDES
```

For this we want to use environment variables:

```properties
jasypt.encryptor.password=${JASYPT_ENCRYPTOR_PASSWORD}
jasypt.encryptor.iv-generator-classname=org.jasypt.iv.NoIvGenerator
jasypt.encryptor.algorithm=${JASYPT_ENCRYPTOR_ALGORITHM}
```

### Secret Management
Good practices:
- Fine-grained access to secrets
- Encryption
- Automate rotating secrets frequently
- Auditing by whom, when and why secrets were accessed

#### Secret Sprawl:
*When we talk about **secret sprawl**, we're really talking about littering our secrets throughout the architecture.*

Common places:
- Properties files
- Environment variables
- Source code
- Configuration management (Jenkins, Ansible, Bamboo, Puppet...)
- Source control (Git or SVN) **!!!DANGER!!!**

*We should centralize our secrets into one location and prevent duplication, and provide clients with fine-grained
(bare-minimum) access to these secrets.*

### Spring Vault
Spring Vault is an abstraction around vault by HashiCorp which can provide functionality to implement good Secret
management as described above.

Dependency:
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-vault-config</artifactId>
</dependency>
```

*Spring Vault also provides support for different Secret backends, like: Consul, RabbitMQ, AWS & different Databases.*

## Authentication & Authorization Exceptions

**Authentication**:  
AuthenticationFilter -> 401 Unauthorized

**Authorization**:  
InterceptorFilter -> 403 Forbidden

### Login Errors
By default Spring redirects the user to the login page with a query parameter:

> localhost:8080/login?error

*We should only provide general messages that credentials were incorrect, to not provide information on the existence
of any user names.*

We could change the behavior and redirect to another page:

```java
http
    .formLogin()
    .loginPage("/login")
	.failureUrl("/login_error")
	.failureHandler(new AuthenticationFailureHandlerImpl())
	.defaultSuccessUrl("/home")
```

By creating an implementation of the AuthenticationFailureHandler we can add custom functionality like logging or
notifying the user by email.

Allthough Spring Security is a good framework, we should prevent providing any information about our security
implementation to the users. For this reason we might want to disable white label error pages:

```properties
server.error.whitelabel.enabled=false
```

Instead, we can provide our own pages in the folder `resources/static/error` and name them `404.html`, or the default
`error.html`, or write our own error controller.

We can find some more information [here](https://www.baeldung.com/spring-boot-custom-error-page).

### Exception Handling
We can also do some custom auditing when there are attempts to unauthorized access. We can do this by writing our own
implementation of the AccessDeniedHandler.

```java
http
    .eceptionHandling()
	.accessDeniedPage("/access_denied")
	.accessDeniedHandler(new AccessDeniedHandlerImpl())
```

### Application Exceptions

*Search fields inside the application should always ring some alarm bells. We want to make sure the exceptions thrown
when the input isn't what we expected don't provide malicious parties with sensitive information, like for instance a
database back end service. This will leave the application open to common SQL injection attacks.
Although using prepared statements protect us from these, we should still provide adequate exception handling.*


---
*Work in progress...*