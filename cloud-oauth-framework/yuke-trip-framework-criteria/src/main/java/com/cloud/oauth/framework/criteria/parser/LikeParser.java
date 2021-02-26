package com.cloud.oauth.framework.criteria.parser;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.enums.SqlLike;
import com.cloud.oauth.framework.criteria.CriteriaContext;
import com.cloud.oauth.framework.criteria.annotation.Like;
import com.cloud.oauth.framework.util.StringUtils;


/**
 * @author zhangzhn
 * @since 2019-06-10
 **/
public class LikeParser extends AbstractGenericParser<Like> {

    @Override
    public Object getDefaultValue(CriteriaContext<Like> criteriaContext) {
        return criteriaContext.getType().defaultValue();
    }

    @Override
    public void doParse(CriteriaContext<Like> criteriaContext, QueryWrapper entityWrapper, String column) {
        if (criteriaContext.getType().reverse()) {
            if (criteriaContext.getType().equals(SqlLike.DEFAULT)) {
                entityWrapper.notLike(column, String.valueOf(criteriaContext.getValue()));
            } else if (criteriaContext.getType().equals(SqlLike.LEFT)) {
                entityWrapper.notLike(column, String.valueOf(criteriaContext.getValue()));
                entityWrapper.likeRight(column, String.valueOf(criteriaContext.getValue()));
            } else {
                entityWrapper.notLike(column, String.valueOf(criteriaContext.getValue()));
                entityWrapper.likeLeft(column, String.valueOf(criteriaContext.getValue()));
            }

        } else {
            if (criteriaContext.getType().equals(SqlLike.DEFAULT)) {
                entityWrapper.like(column, String.valueOf(criteriaContext.getValue()));
            } else if (criteriaContext.getType().equals(SqlLike.LEFT)) {
                entityWrapper.likeLeft(column, String.valueOf(criteriaContext.getValue()));
            } else if (criteriaContext.getType().equals(SqlLike.RIGHT)) {
                entityWrapper.likeRight(column, String.valueOf(criteriaContext.getValue()));
            }
        }
    }

    @Override
    public String[] getColumns(CriteriaContext<Like> criteriaContext) {
        return criteriaContext.getType().columns();
    }

    @Override
    public String getGroup(Like like) {
        return like.group();
    }

    @Override
    public boolean allowEmpty(Like like) {
        return like.allowEmpty() || StringUtils.isNotEmpty(like.defaultValue());
    }

    @Override
    public boolean isEnable(Like like) {
        return like.enable();
    }
}
