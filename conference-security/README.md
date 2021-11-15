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

## Custom Security Object

- We extend the Security Core User object:
  ```java
  import org.springframework.security.core.userdetails.User;

  public class ConferenceUserDetails extends User {  
       private String nickname;
	 
       public ConferenceUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {  
         super(username, password, authorities);  
       }  

       // Getter & Setter
  }
  ```

- Context Mapper
  ```java
  @Service  
  public class ConferenceUserDetailsContextMapper implements UserDetailsContextMapper {  
       private final DataSource dataSource;  

       public ConferenceUserDetailsContextMapper(DataSource dataSource) {  
           this.dataSource = dataSource;  
       }  

       private final static String LOAD_USER_BY_USERNAME_QUERY =  
              "SELECT username, password, enabled, nickname FROM users WHERE username = ?";
              
       @Override  
       public UserDetails mapUserFromContext(
                      DirContextOperations dirContextOperations,
                      String s,
                      Collection<? extends GrantedAuthority> collection) {  
           JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);  
           final ConferenceUserDetails userDetails = new ConferenceUserDetails(  
               dirContextOperations.getStringAttribute("uid"),  
               "fake",  
               Collections.emptyList()  
           );  
           jdbcTemplate.queryForObject(LOAD_USER_BY_USERNAME_QUERY, new RowMapper<ConferenceUserDetails>() {  
               @Override  
               public ConferenceUserDetails mapRow(ResultSet resultSet, int i) throws SQLException {  
                   userDetails.setNickname(resultSet.getString("nickname"));  
                   return userDetails;  
               }  
           }, dirContextOperations.getStringAttribute("uid"));  
           return userDetails;  
       }  

       @Override  
       public void mapUserToContext(UserDetails userDetails, DirContextAdapter dirContextAdapter) { }  
  }
  ```

- In `WebSecurityConfigurerAdapter` we wire in the bean and add it to our configurations:
  ```java
  @Autowired  
  private ConferenceUserDetailsContextMapper ctxMapper;

  // ...

  @Override  
  protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
  	  auth  
    	  // ...
 		  .and()  
 		  .userDetailsContextMapper(ctxMapper);
  }
  ```

## Securing Methods in Spring Security
### Enabling Annotations

- @EnableGlobalMethodSecurity:
```java
@Configuration  
@EnableWebSecurity  
@EnableGlobalMethodSecurity(  
 prePostEnabled = true,  
 securedEnabled = true,  
 jsr250Enabled = true)  
public class ConferenceSecurityConfig extends WebSecurityConfigurerAdapter {
	// ...
}
```

- @Secured
```java
@PostMapping("registration")
@Secured("ROLE_ADMIN")
public String addRegistration(@Valid @ModelAttribute("registration") Registration registration) {
	// ...
}
```

## Security JSP Taglibs

Dependency:
```xml
<dependency>
    <groupId>org.springframework.security</groupId>
    <artifactId>spring-security-taglibs</artifactId>
</dependency>
```

Taglib:
```html
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<sec:authorize access="!isAuthenticated()">
  <a href="login">Login</a>
</sec:authorize>
```

## Principal Injection

We can easily get the principal from a user just by injecting it in the called method:
```java
@PostMapping("registration")
@Secured("ROLE_USER")
public String addRegistration(
            @Valid @ModelAttribute ("registration") Registration registration,
            BindingResult result,
            Authentication auth) {
    System.out.println("AUTHENTICATION PRINCIPAL = " + auth.getPrincipal());
    // ...
}
```

From this principal we can get following fields:
- Username
- Password: [PROTECTED]
- Enabled
- AccountNonExpired
- CredentialsNonExpired
- AccountNonLocked
- Granted Authorities