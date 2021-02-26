package com.cloud.oauth.framework.base.pagination;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.cloud.oauth.framework.util.StringUtils;
import lombok.Data;

/**
 * @author zhangzhn
 * @since 2019-06-10
 **/
@Data
public class Pagination<T> {

    /**
     * 升序
     */
    public final static String ASC = "asc";
    /**
     * 降序
     */
    public final static String DESC = "desc";

    //@ApiModelProperty("每页数据条数")
    private Long pageSize;
    //@ApiModelProperty("分页页码")
    private Long pageNumber;
    //@ApiModelProperty("排序字段")
    private String orderBy;
    //@ApiModelProperty("排序顺序")
    private String orderDirection;

    public Page<T> getPage() {
        Long pageSize = this.pageSize != null ? this.pageSize : 20;
        Long pageNumber = this.pageNumber != null ? this.pageNumber : 1;
        Page<T> page = new Page<T>(pageNumber, pageSize);
        if (StringUtils.isNotEmpty(orderBy)) {
            if (ASC.equals(orderDirection)) {
                page.setAsc(orderBy);
            } else {
                page.setDesc(orderBy);
            }
        }
        return page;
    }
}
