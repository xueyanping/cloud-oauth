package com.cloud.oauth.uaa.config;

import com.cloud.oauth.uaa.exception.WebResponseExceptionTranslator;
import com.cloud.oauth.uaa.integration.IntegrationAuthenticationFilter;
import com.cloud.oauth.uaa.service.DatabaseCachableClientDetailsService;
import com.cloud.oauth.uaa.service.IntegrationUserDetailsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * @author zhangzhn
 * @since 2019-06-10
 **/
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    private RedisConnectionFactory redisConnectionFactory;

    private IntegrationUserDetailsService integrationUserDetailsService;

    private AuthenticationManager authenticationManager;

    private WebResponseExceptionTranslator webResponseExceptionTranslator;

    private DatabaseCachableClientDetailsService cachableClientDetailsService;

    private IntegrationAuthenticationFilter integrationAuthenticationFilter;

    private int expireTime;


    public AuthorizationServerConfiguration(RedisConnectionFactory redisConnectionFactory,
                                            AuthenticationManager authenticationManager,
                                            IntegrationUserDetailsService integrationUserDetailsService,
                                            WebResponseExceptionTranslator webResponseExceptionTranslator,
                                            IntegrationAuthenticationFilter integrationAuthenticationFilter,
                                            DatabaseCachableClientDetailsService redisClientDetailsService,
                                            @Value("${security.oauth2.token.expireTime:30}") int expireTime) {
        this.redisConnectionFactory = redisConnectionFactory;
        this.authenticationManager = authenticationManager;
        this.integrationUserDetailsService = integrationUserDetailsService;
        this.webResponseExceptionTranslator = webResponseExceptionTranslator;
        this.integrationAuthenticationFilter = integrationAuthenticationFilter;
        this.cachableClientDetailsService = redisClientDetailsService;
        this.expireTime = expireTime;
    }


    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(cachableClientDetailsService);
//        clients.inMemory()
//                .withClient("browser")
//                .secret(passwordEncoder.encode("server"))
//                .scopes("ui")
//                .authorizedGrantTypes("refresh_token","authorization_code","password","client_credentials")
//                .accessTokenValiditySeconds(expireTime)
//                .refreshTokenValiditySeconds(86400)
//                .authorities("p2")
//                .and()
//                .withClient("server")
//                .secret(passwordEncoder.encode("server"))
//                .scopes("server")
//                .authorizedGrantTypes("refresh_token","authorization_code","password","client_credentials")
//                .accessTokenValiditySeconds(expireTime)
//                .refreshTokenValiditySeconds(86400)
//                .authorities("p2");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        CustomerTokenServices tokenServices = new CustomerTokenServices();
        //token持久化容器
        tokenServices.setTokenStore(new CustomRedisTokenStore(redisConnectionFactory));
        //是否支持refresh_token，默认false
        tokenServices.setSupportRefreshToken(true);
        //客户端信息
        tokenServices.setClientDetailsService(endpoints.getClientDetailsService());
        //自定义token生成
        //tokenServices.setTokenEnhancer(tokenEnhancer());
        //access_token 的有效时长 (秒), 默认 12 小时
        tokenServices.setAccessTokenValiditySeconds(60 * expireTime);
        //refresh_token 的有效时长 (秒), 默认 30 天
        tokenServices.setRefreshTokenValiditySeconds(-1);
        //是否复用refresh_token,默认为true(如果为false,则每次请求刷新都会删除旧的refresh_token,创建新的refresh_token)
        tokenServices.setReuseRefreshToken(true);
        endpoints
                .tokenStore(new RedisTokenStore(redisConnectionFactory))
//                .accessTokenConverter(jwtAccessTokenConverter())
                .authenticationManager(authenticationManager)
                .exceptionTranslator(webResponseExceptionTranslator)
                .tokenServices(tokenServices)
                .userDetailsService(integrationUserDetailsService);
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
