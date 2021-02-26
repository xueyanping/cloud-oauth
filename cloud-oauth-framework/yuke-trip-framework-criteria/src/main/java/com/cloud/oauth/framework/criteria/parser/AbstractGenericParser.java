package com.cloud.oauth.framework.criteria.parser;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ArrayUtils;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.cloud.oauth.framework.criteria.CriteriaContext;
import org.apache.commons.beanutils.ConvertUtils;

import java.lang.annotation.Annotation;

/**
 * 通用解析器，主要是处理了字段解析的部分
 *
 * @author zhangzhn
 * @since 2019-06-10
 **/
public abstract class AbstractGenericParser<S extends Annotation> extends AbstractParser<S> {

    @Override
    public void parse(CriteriaContext<S> criteriaContext, QueryWrapper entityWrapper) {

        this.transferValue(criteriaContext);

        if (criteriaContext.getValue() == null && !this.allowEmpty(criteriaContext.getType())) {
            throw new IllegalArgumentException("The condition is not allowed to be empty but the value is empty");
        }

        String[] columns = this.getColumns(criteriaContext);
        if (ArrayUtils.isNotEmpty(columns)) {
            for (String column : columns) {
                this.doParse(criteriaContext, entityWrapper, column);
            }
        } else {
            this.doParse(criteriaContext, entityWrapper, this.getDefaultColumn(criteriaContext));
        }
    }

    private void transferValue(CriteriaContext<S> criteriaContext) {
        //获取值
        Object value = criteriaContext.getValue();
        //获取默认值
        Object defaultValue = this.getDefaultValue(criteriaContext);

        //如果值为空则重置为默认值
        if (ObjectUtils.isNull(value) && defaultValue != null && !defaultValue.getClass().equals(criteriaContext.getDataType())) {
            value = ConvertUtils.convert(this.getDefaultValue(criteriaContext), criteriaContext.getDataType());
        }
        //重置值
        criteriaContext.setValue(value);
    }

    /**
     * 获取值，页面传过来的或者是默认值
     *
     * @param criteriaContext
     * @return
     */
    public abstract Object getDefaultValue(CriteriaContext<S> criteriaContext);

    /**
     * 真正的拼写条件
     *
     * @param criteriaContext
     * @param entityWrapper
     * @param column
     */
    public abstract void doParse(CriteriaContext<S> criteriaContext, QueryWrapper entityWrapper, String column);

    /**
     * 获取列
     *
     * @param criteriaContext
     * @return
     */
    public abstract String[] getColumns(CriteriaContext<S> criteriaContext);
}
