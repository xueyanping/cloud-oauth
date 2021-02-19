package com.cloud.oauth.framework.oauth2.feign;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author zhangzhn
 * @since 2019-06-10
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(OAuth2ClientFeignConfiguration.class)
public @interface EnableOAuth2ClientFeign {
}
