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
- Github
- Google
- Okta
- ...

## Facebook Configuration
Go [here](https://developers.facebook.com/) and create a new app:
- Create
- Build Connected experiences
- Name the app
- Add Facebook Login

Only for testing purposes: 
- In top left dropdown containing the application name
  - Select 'Create Test App'
- Go to Settings > Basic and copy:
  - App ID
  - App Secret

In production application:
- Enter valid redirect URI

Application Properties:
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

## Actors

- Protected Resource
- Resource Server (e.g Facebook)
- Resource Owner
- Client (Our application)
- Authorization Server
- Access Token

## Flow

The authorization server authenticates the client and resource owner.
It then asks the Resource owner to authorize the client to access the protected resource.
Only then it issues an access token to the client that can be used to access the protected resource.

- Authorization Code Grant Flow

## Curl With Access Token

> curl --header "Authorization: Bearer ${TOKEN}" https://graph.facebook.com/me?fields-id,name-email

Return:
```json
{"name":"Your Name","id":"1234567891234567"}
```

*We can get the token from the authenticationResult in OAuth2AuthorizationCodeAuthenticationProvider.*

## OpenID Connect

A standard of authentication built on top of OAuth 2.0. It consolidates best practices in a common specification
and creates consistency across all OpenID Connect certified providers, to make life easier for developers.

- Provides an identity layer on top of OAuth 2.0 protocol.
- Clients can verify the identity of an end-user based on the authentication performed by an authorization server.
- Gives clients access to basic profile information of the end-user.

## Google Configuration

- Register with [Google developer pages](https://developers.google.com/).
- Go to [Credentials](https://console.developers.google.com/apis/credentials).
- Create credentials
- Oauth Client ID
- Provided URI
  - http://localhost:8080, https://localhost:8443
- Provide Redirect URI
  - http://localhost:8080/login/oauth2/code/google

Application Properties:
```yaml
spring:
  security:
    oauth2:
      client:
        registration:
          facebook:
            client-id: ${GOOGLE_CLIENT_ID}
            client-secret: ${GOOGLE_CLIENT_SECRET}
```

## ID Token

[Open ID Source](https://openid.net/specs/openid-connect-core-1_0.html#IDToken) on the ID Token.


---
*Work in progress...*