package com.cloud.oauth.framework.criteria.parser;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cloud.oauth.framework.criteria.CriteriaContext;
import com.cloud.oauth.framework.criteria.annotation.Eq;
import org.apache.commons.lang3.StringUtils;

/**
 * 等于条件解析
 *
 * @author zhangzhn
 * @since 2019-06-10
 **/
public class EqParser extends AbstractGenericParser<Eq> {

    @Override
    public Object getDefaultValue(CriteriaContext<Eq> criteriaContext) {
        return criteriaContext.getType().defaultValue();
    }

    @Override
    public void doParse(CriteriaContext<Eq> criteriaContext, QueryWrapper entityWrapper, String column) {
        if (criteriaContext.getType().reverse()) {
            entityWrapper.ne(column, criteriaContext.getValue());
        } else {
            entityWrapper.eq(column, criteriaContext.getValue());
        }
    }

    @Override
    public String[] getColumns(CriteriaContext<Eq> criteriaContext) {
        return criteriaContext.getType().columns();
    }

    @Override
    public String getGroup(Eq eq) {
        return eq.group();
    }

    @Override
    public boolean allowEmpty(Eq eq) {
        return eq.allowEmpty() || StringUtils.isNotEmpty(eq.defaultValue());
    }

    @Override
    public boolean isEnable(Eq eq) {
        return eq.enable();
    }
}
