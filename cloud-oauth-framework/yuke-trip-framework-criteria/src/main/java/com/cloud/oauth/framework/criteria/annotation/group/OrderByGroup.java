package com.cloud.oauth.framework.criteria.annotation.group;


import com.cloud.oauth.framework.criteria.annotation.OrderBy;

import java.lang.annotation.*;

/**
 * @author zhangzhn
 * @since 2019-06-10
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OrderByGroup {

    OrderBy[] value();
}
