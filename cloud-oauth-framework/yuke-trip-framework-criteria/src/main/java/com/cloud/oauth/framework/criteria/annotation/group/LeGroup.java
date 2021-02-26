package com.cloud.oauth.framework.criteria.annotation.group;


import com.cloud.oauth.framework.criteria.annotation.Le;

import java.lang.annotation.*;

/**
 * @author zhangzhn
 * @since 2019-06-10
 **/
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LeGroup {

    Le[] value();
}
