package com.cloud.oauth.framework.criteria.parser;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cloud.oauth.framework.criteria.CriteriaContext;
import com.cloud.oauth.framework.criteria.annotation.Gt;
import com.cloud.oauth.framework.util.StringUtils;


/**
 * 大于等于条件解析
 *
 * @author zhangzhn
 * @since 2019-06-10
 **/
public class GtParser extends AbstractGenericParser<Gt> {

    @Override
    public Object getDefaultValue(CriteriaContext<Gt> criteriaContext) {
        return criteriaContext.getType().defaultValue();
    }

    @Override
    public void doParse(CriteriaContext<Gt> criteriaContext, QueryWrapper entityWrapper, String column) {
        entityWrapper.gt(column, criteriaContext.getValue());
    }

    @Override
    public String[] getColumns(CriteriaContext<Gt> criteriaContext) {
        return criteriaContext.getType().columns();
    }

    @Override
    public String getGroup(Gt gt) {
        return gt.group();
    }

    @Override
    public boolean allowEmpty(Gt gt) {
        return gt.allowEmpty() || StringUtils.isNotEmpty(gt.defaultValue());
    }

    @Override
    public boolean isEnable(Gt gt) {
        return gt.enable();
    }
}
