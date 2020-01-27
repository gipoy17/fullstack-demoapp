package com.demo.miniapp.authentication;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "auth")
public class AuthPropConfig {
    private String secret;
    private long expirationTime;
    private String tokenPrefix;
    private String authorizationHeader;
}
