package com.cloud.oauth.framework.criteria.annotation;



import com.cloud.oauth.framework.criteria.annotation.group.BetweenAndGroup;

import java.lang.annotation.*;


/**
 * @author zhangzhn
 * @since 2019-06-10
 **/
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Repeatable(BetweenAndGroup.class)
public @interface BetweenAnd {

    /**
     * 是否允许控制
     *
     * @return
     */
    boolean allowEmpty() default false;

    /**
     * 是否是结束参数
     *
     * @return
     */
    String end();

    /**
     * 指定的column
     *
     * @return
     */
    String[] columns() default {};

    /**
     * 分组
     *
     * @return
     */
    String group() default "";

    boolean enable() default true;

    /**
     * 默认起始值
     *
     * @return
     */
    String defaultStartValue() default "";

    /**
     * 默认结束值
     *
     * @return
     */
    String defaultEndValue() default "";


    /**
     * 反转，反转为 not between and
     *
     * @return
     */
    boolean reverse() default false;

}
