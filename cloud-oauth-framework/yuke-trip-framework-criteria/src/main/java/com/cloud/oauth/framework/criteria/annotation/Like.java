package com.cloud.oauth.framework.criteria.annotation;

import com.baomidou.mybatisplus.core.enums.SqlLike;
import com.cloud.oauth.framework.criteria.annotation.group.LikeGroup;

import java.lang.annotation.*;

/**
 * @author zhangzhn
 * @since 2019-06-10
 **/
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Repeatable(LikeGroup.class)
public @interface Like {
    /**
     * 允许为空
     *
     * @return
     */
    boolean allowEmpty() default false;

    /**
     * LIKE类型
     *
     * @return
     */
    SqlLike likeType() default SqlLike.DEFAULT;

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
     * 反转，反转为 not like
     *
     * @return
     */
    boolean reverse() default false;

    /**
     * 是否启用
     *
     * @return
     */
    boolean enable() default true;
}
