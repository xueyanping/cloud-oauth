package com.cloud.oauth.framework.criteria.parser;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cloud.oauth.framework.criteria.CriteriaContext;
import com.cloud.oauth.framework.criteria.annotation.Ge;
import com.cloud.oauth.framework.util.StringUtils;


/**
 * 大于等于条件解析
 *
 * @author zhangzhn
 * @since 2019-06-10
 **/
public class GeParser extends AbstractGenericParser<Ge> {

    @Override
    public Object getDefaultValue(CriteriaContext<Ge> criteriaContext) {
        return criteriaContext.getType().defaultValue();
    }

    @Override
    public void doParse(CriteriaContext<Ge> criteriaContext, QueryWrapper entityWrapper, String column) {
        entityWrapper.ge(column, criteriaContext.getValue());
    }

    @Override
    public String[] getColumns(CriteriaContext<Ge> criteriaContext) {
        return criteriaContext.getType().columns();
    }

    @Override
    public String getGroup(Ge ge) {
        return ge.group();
    }

    @Override
    public boolean allowEmpty(Ge ge) {
        return ge.allowEmpty() || StringUtils.isNotEmpty(ge.defaultValue());
    }

    @Override
    public boolean isEnable(Ge ge) {
        return ge.enable();
    }
}
