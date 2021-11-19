# Oauth2
The auth in Oauth2:
= Authorization
≠ Authentication

## Dependency
```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-oauth2-client</artifactId>
</dependency>
```

## Social Login
- Facebook
- Google
- ...

## Facebook
Go [here](https://developers.facebook.com/) and create a new app:
- Create
- Build Connected experiences
- Name the app
- Add Facebook Login
- In top left dropdown containing the application name
  - Select 'Create Test App'
- Go to Settings > Basic and copy:
  - App ID
  - App Secret

Properties:
```yaml
spring:
  security:
    oauth2:
      client:
        registration:
          facebook:
            client-id: ${FACEBOOK_CLIENT_ID}
            client-secret: ${FACEBOOK_CLIENT_SECRET}
```

---
*Work in progress...*