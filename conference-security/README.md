# Authenticating Users with Spring Security
*Spring Security fundamentals*

- Authenticating users against LDAP
- Customizing the Spring Security object
- Create a custom registration process
- Handling forgotten passwords

## Hard Coded Authentication

Although not advised in production we can hard code our authentication in the `WebSecurityConfigurerAdapter`:

```java
@Override  
protected void configure(final AuthenticationManagerBuilder auth) throws Exception {  
    auth
	    .inMemoryAuthentication()  
        .withUser("user")  
        .password(passwordEncoder().encode("pass"))  
        .roles("USER");  
}
```


## JDBC Authentication

We can add our authentication and authorization to a database fairly easily in the `WebSecurityConfigurerAdapter`:

```java
@Override  
protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
    auth
        .jdbcAuthentication()
        .dataSource(dataSource);  
}
```

This is what our database needs to look like:

```sql
CREATE TABLE users (  
    username VARCHAR(50) NOT NULL,  
    password VARCHAR(100) NOT NULL,  
    enabled TINYINT NOT NULL DEFAULT 1,  
    PRIMARY KEY (username)  
);  
  
CREATE TABLE authorities (  
    username VARCHAR(50) NOT NULL,  
    authority VARCHAR(50) NOT NULL,  
    FOREIGN KEY (username) REFERENCES users(username)  
);  
  
CREATE UNIQUE INDEX ix_auth_username on authorities (username,authority);  
  
INSERT INTO users (username, password, enabled)  
VALUES('user','$2a$10$PwLlfto2SjmfEqruxwQ3AON3PppXx/KzPldGY8Hm9DMwTy5JY2zr6',1);  
  
INSERT INTO authorities (username, authority)  
VALUES('user', 'ROLE_USER');
```

## LDAP Authentication

*Lightweight Directory Access Protocol (LDAP) is a networkprotocol that describes how to access data from
directory services over, for example, TCP/IP. LDAP uses the LDAP Data Interchange Format (LDIF).
This is an ASCII format used to add data to the LDAP hierarchical database.*

Dependencies for embedded LDAP server:
```xml
<dependency>  
 	<groupId>org.springframework.ldap</groupId>  
	<artifactId>spring-ldap-core</artifactId>  
</dependency>  
<dependency>  
 	<groupId>org.springframework.security</groupId>  
 	<artifactId>spring-security-ldap</artifactId>  
</dependency>  
<dependency>  
 	<groupId>com.unboundid</groupId>  
 	<artifactId>unboundid-ldapsdk</artifactId>  
</dependency>
```

Configuration in `WebSecurityConfigurerAdapter`:
```java
auth  
    .ldapAuthentication()  
 	.userDnPatterns("uid={0},ou=people")  
 	.groupSearchBase("ou=groups")  
 	.contextSource()  
 	.url("ldap://localhost:8389/dc=mydomain,dc=com")  
 	.and()  
 	.passwordCompare()  
 	.passwordEncoder(passwordEncoder())  
 	.passwordAttribute("userPassword");
```

Properties:
```properties
spring.ldap.embedded.ldif=classpath:test-server.ldif  
spring.ldap.embedded.base-dn=dc=mydomain,dc=com  
spring.ldap.embedded.port=8389
```

Here is an [Example LDAP Server Configuration](src/main/resources/test-server.ldif).