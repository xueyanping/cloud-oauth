package com.cloud.oauth.framework.criteria.annotation;


import com.cloud.oauth.framework.criteria.annotation.group.EqGroup;

import java.lang.annotation.*;

/**
 * @author zhangzhn
 * @since 2019-06-10
 **/
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Repeatable(EqGroup.class)
public @interface Eq {

    /**
     * 允许为空
     *
     * @return
     */
    boolean allowEmpty() default false;

    /**
     * 字段名称
     *
     * @return
     */
    String[] columns() default {};

    /**
     * 查询分组
     *
     * @return
     */
    String group() default "";

    /**
     * 默认值
     *
     * @return
     */
    String defaultValue() default "";

    /**
     * 是否启用
     *
     * @return
     */
    boolean enable() default true;


    /**
     * 反转，反转为 <>
     *
     * @return
     */
    boolean reverse() default false;
}
