package com.cloud.oauth.framework.base.pagination;

import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.oauth.framework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;


/**
 * @author zhangzhn
 * @since 2019-06-10
 **/
public class PaginationUtils {

    /**
     * 每页记录条数的请求参数名称
     */
    private static final String PAGE_SIZE = "pageSize";

    /**
     * 当前页
     */
    private static final String CURRENT_PAGE = "pageNumber";

    /**
     * 排序字段
     */
    private static final String SORT = "sort";

    /**
     * asc或者desc
     */
    private static final String ORDER = "order";

    private static final String ORDER_ASC = "asc";

    public static <T> Page<T> buildFromRequest(HttpServletRequest request) {
        if (request == null) {
            throw new RuntimeException("请检查request对象是否注入!");
        }

        String pageSize = request.getParameter(PAGE_SIZE);
        if (StringUtils.isEmpty(pageSize)) {
            pageSize = "20";
        }

        String currentPage = request.getParameter(CURRENT_PAGE);
        if (StringUtils.isEmpty(currentPage)) {
            currentPage = "1";
        }

        Assert.isTrue(StringUtils.isNumeric(pageSize), "分页参数：PAGE_SIZE参数不是数字");
        Assert.isTrue(StringUtils.isNumeric(currentPage), "分页参数：CURRENT_PAGE参数不算数字");

        long limit = Long.valueOf(pageSize);
        long current = Long.valueOf(currentPage);
        String sort = request.getParameter(SORT);
        String order = request.getParameter(ORDER);
        if (StringUtils.isEmpty(sort)) {
            Page<T> page = new Page<T>(current, limit);
            return page;
        } else {
            Page<T> page = new Page<>(current, limit);
            if (ORDER_ASC.equals(order)) {
                page.setAsc(sort);
            } else {
                page.setDesc(sort);
            }
            return page;
        }
    }
}
