package com.cloud.oauth.framework.criteria.parser;



import com.cloud.oauth.framework.criteria.CriteriaContext;
import com.cloud.oauth.framework.criteria.annotation.Parser;
import com.cloud.oauth.framework.util.StringUtils;

import java.lang.annotation.Annotation;

/**
 * @author zhangzhn
 * @since 2019-06-10
 **/
@Parser
public abstract class AbstractParser<S extends Annotation> implements ConditionParser<S> {

    /**
     * 获取默认的列,自动将驼峰表示转换成下划线格式
     *
     * @param criteriaContext
     * @return
     */
    protected String getDefaultColumn(CriteriaContext criteriaContext) {
        return StringUtils.camelCaseToUnderline(criteriaContext.getProperty());
    }
}
