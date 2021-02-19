package com.cloud.oauth.framework.oauth2.token;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerTokenServicesConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhangzhn
 * @since 2019-06-10
 **/
@Configuration
@AutoConfigureBefore(ResourceServerTokenServicesConfiguration.class)
public class UserPrincipalExtractorConfiguration {

    @Bean
    public UserPrincipalExtractor userPrincipalExtractor() {
        return new UserPrincipalExtractor();
    }
}
