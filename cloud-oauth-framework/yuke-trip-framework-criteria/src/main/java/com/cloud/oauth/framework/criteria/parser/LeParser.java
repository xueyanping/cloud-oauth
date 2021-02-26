package com.cloud.oauth.framework.criteria.parser;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.cloud.oauth.framework.criteria.CriteriaContext;
import com.cloud.oauth.framework.criteria.annotation.Le;
import org.apache.commons.lang3.StringUtils;

/**
 * 大于等于条件解析
 *
 * @author zhangzhn
 * @since 2019-06-10
 **/
public class LeParser extends AbstractGenericParser<Le> {

    @Override
    public Object getDefaultValue(CriteriaContext<Le> criteriaContext) {
        return criteriaContext.getType().defaultValue();
    }

    @Override
    public void doParse(CriteriaContext<Le> criteriaContext, QueryWrapper entityWrapper, String column) {
        entityWrapper.ge(column, criteriaContext.getValue());
    }

    @Override
    public String[] getColumns(CriteriaContext<Le> criteriaContext) {
        return criteriaContext.getType().columns();
    }

    @Override
    public String getGroup(Le eq) {
        return eq.group();
    }

    @Override
    public boolean allowEmpty(Le eq) {
        return eq.allowEmpty() || StringUtils.isNotEmpty(eq.defaultValue());
    }

    @Override
    public boolean isEnable(Le eq) {
        return eq.enable();
    }
}
