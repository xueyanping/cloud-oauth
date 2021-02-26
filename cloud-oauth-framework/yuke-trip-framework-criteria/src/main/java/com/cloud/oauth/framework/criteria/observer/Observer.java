package com.cloud.oauth.framework.criteria.observer;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cloud.oauth.framework.criteria.CriteriaContext;

/**
 * @author zhangzhn
 * @since 2019-06-10
 **/
public interface Observer<T> {

    /**
     * 解析之前
     *
     * @param wrapper
     */
    public void beforeParse(QueryWrapper<T> wrapper);

    /**
     * 解析之后
     *
     * @param wrapper
     */
    public void afterParse(QueryWrapper<T> wrapper);

    /**
     * 正在解析
     *
     * @param criteriaContext
     * @param wrapper
     * @return
     */
    public boolean onParse(CriteriaContext<?> criteriaContext, QueryWrapper<T> wrapper);
}
