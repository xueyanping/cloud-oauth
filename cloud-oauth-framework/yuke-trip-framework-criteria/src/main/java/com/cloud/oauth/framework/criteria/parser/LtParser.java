package com.cloud.oauth.framework.criteria.parser;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cloud.oauth.framework.criteria.CriteriaContext;
import com.cloud.oauth.framework.criteria.annotation.Lt;
import com.cloud.oauth.framework.util.StringUtils;


/**
 * 大于等于条件解析
 *
 * @author zhangzhn
 * @since 2019-06-10
 **/
public class LtParser extends AbstractGenericParser<Lt> {

    @Override
    public Object getDefaultValue(CriteriaContext<Lt> criteriaContext) {
        return criteriaContext.getType().defaultValue();
    }

    @Override
    public void doParse(CriteriaContext<Lt> criteriaContext, QueryWrapper entityWrapper, String column) {
        entityWrapper.ge(column, criteriaContext.getValue());
    }

    @Override
    public String[] getColumns(CriteriaContext<Lt> criteriaContext) {
        return criteriaContext.getType().columns();
    }

    @Override
    public String getGroup(Lt lt) {
        return lt.group();
    }

    @Override
    public boolean allowEmpty(Lt lt) {
        return lt.allowEmpty() || StringUtils.isNotEmpty(lt.defaultValue());
    }

    @Override
    public boolean isEnable(Lt lt) {
        return lt.enable();
    }
}
