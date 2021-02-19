package com.cloud.oauth.framework.security.access;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 开启安全访问，根据UPM中权限进行判断
 *
 * @author zhangzhn
 * @since 2019-06-10
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(SecurityAccessAutoConfiguration.class)
public @interface EnableSecurityAccess {
}
