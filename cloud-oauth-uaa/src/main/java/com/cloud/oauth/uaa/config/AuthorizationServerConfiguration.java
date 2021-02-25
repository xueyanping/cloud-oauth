package com.cloud.oauth.uaa.config;

import com.cloud.oauth.uaa.integration.IntegrationAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

/**
 * @author zhangzhn
 * @since 2019-06-10
 **/
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    private final IntegrationAuthenticationFilter integrationAuthenticationFilter;
    private int expireTime;

    public AuthorizationServerConfiguration(IntegrationAuthenticationFilter integrationAuthenticationFilter,
                                            @Value("${security.oauth2.token.expireTime:30}") int expireTime) {
        this.integrationAuthenticationFilter = integrationAuthenticationFilter;
        this.expireTime = expireTime;
    }


    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("browser")
                .secret(passwordEncoder.encode("server"))
                .scopes("ui")
                .authorizedGrantTypes("refresh_token","authorization_code","password","client_credentials")
                .accessTokenValiditySeconds(1800)
                .refreshTokenValiditySeconds(86400)
                .authorities("p2")
                .and()
                .withClient("server")
                .secret(passwordEncoder.encode("server"))
                .scopes("server")
                .authorizedGrantTypes("refresh_token","authorization_code","password","client_credentials")
                .accessTokenValiditySeconds(1800)
                .refreshTokenValiditySeconds(86400)
                .authorities("p2");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.authenticationManager(authenticationManager);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.allowFormAuthenticationForClients()
                .tokenKeyAccess("isAuthenticated()")
                .checkTokenAccess("permitAll()")
                .addTokenEndpointAuthenticationFilter(integrationAuthenticationFilter);
    }


    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey("cloud-oauth");
        return jwtAccessTokenConverter;
    }
}
