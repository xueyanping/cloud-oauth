package com.cloud.oauth.framework.criteria.observer;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cloud.oauth.framework.criteria.CriteriaContext;

/**
 * @author zhangzhn
 * @since 2019-06-10
 **/
public class CriteriaObserver<T> implements Observer<T> {

    @Override
    public void beforeParse(QueryWrapper<T> wrapper) {

    }

    @Override
    public void afterParse(QueryWrapper<T> wrapper) {

    }

    @Override
    public boolean onParse(CriteriaContext<?> criteriaContext, QueryWrapper<T> wrapper) {
        return false;
    }
}
