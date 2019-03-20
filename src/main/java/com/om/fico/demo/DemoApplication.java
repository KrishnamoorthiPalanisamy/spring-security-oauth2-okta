package com.om.fico.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@SpringBootApplication
@EnableOAuth2Client
@RestController
public class DemoApplication extends AuthorizationServerConfigurerAdapter {


    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }


    @Override
    public void configure(final ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory().withClient("oktasecurity").secret("oktasecrate")
                .authorizedGrantTypes("password", "authorization_code", "refresh_token", "client_credentials")
                .scopes("foo", "read", "write").accessTokenValiditySeconds(3600)
                // 1 hour
                .refreshTokenValiditySeconds(2592000)
                // 30 days
                .redirectUris("http://localhost:8080/");
    }


    @GetMapping("/")
    public Principal get(Principal principal) {
        return principal;
    }


}
