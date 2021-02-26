package com.cloud.oauth.framework.criteria.parser;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cloud.oauth.framework.criteria.CriteriaContext;

import java.lang.annotation.Annotation;

/**
 * @author zhangzhn
 **/
public interface ConditionParser<S extends Annotation> {

    /**
     * 解析条件带分组
     *
     * @param criteriaContext
     * @param entityWrapper
     * @return
     */
    void parse(CriteriaContext<S> criteriaContext, QueryWrapper entityWrapper);

    /**
     * 获取分组
     *
     * @param s
     * @return
     */
    String getGroup(S s);

    /**
     * 是否允许为空
     *
     * @param s
     * @return
     */
    boolean allowEmpty(S s);

    /**
     * 是否启用
     *
     * @param s
     * @return
     */
    boolean isEnable(S s);
}
