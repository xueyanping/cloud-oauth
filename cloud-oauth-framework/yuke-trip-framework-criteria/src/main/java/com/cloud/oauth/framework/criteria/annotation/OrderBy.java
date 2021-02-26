package com.cloud.oauth.framework.criteria.annotation;


import com.cloud.oauth.framework.criteria.annotation.group.OrderByGroup;

import java.lang.annotation.*;

/**
 * @author zhangzhn
 * @since 2019-06-10
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Repeatable(OrderByGroup.class)
public @interface OrderBy {

    /**
     * 列名
     *
     * @return
     */
    String[] columns();

    /**
     * 是否顺序排序
     *
     * @return
     */
    boolean isAsc() default true;

    /**
     * 是否启用
     *
     * @return
     */
    boolean enable() default true;

    /**
     * 分组
     *
     * @return
     */
    String group() default "";

}
