package com.cloud.oauth.framework.criteria.annotation;

import java.lang.annotation.*;

/**
 * 用于指定条件的顺序
 *
 * @author zhangzhn
 * @since 2019-06-10
 **/
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Order {
    int value() default 0;
}
