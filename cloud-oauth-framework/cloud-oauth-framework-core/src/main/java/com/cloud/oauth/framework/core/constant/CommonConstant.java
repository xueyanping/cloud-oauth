package com.cloud.oauth.framework.core.constant;

/**
 * 平台常量
 *
 * @author zhangzhn
 * @since 2019-06-10
 **/
public class CommonConstant {

    /**
     * 平台菜单级别，0:平台级，1:租户级 2:运营级
     */
    public static final Integer RESOURCE_LEVEL_PLATFORM = 0;
    public static final Integer RESOURCE_LEVEL_TENANT = 1;
    public static final Integer RESOURCE_LEVEL_OPERATOR = 2;
    public static final String RESOURCE_CACHE_NAME = "resourceCache";

    public static final String COMMON_YES = "Y";
    public static final String COMMON_NO = "N";
    /**
     * 平台资源类型：目录
     */
    public static final Integer RESOURCE_TYPE_DIRECTORY = 0;
    /**
     * 平台资源类型：菜单
     */
    public static final Integer RESOURCE_TYPE_MENU = 1;
    /**
     * 平台资源类型：功能
     */
    public static final Integer RESOURCE_TYPE_FUNCTION = 2;

    /**
     * 系统管理员角色代码
     */
    public static final String ROLE_ADMIN = "admin";
    /**
     * 租户管理员角色代码
     */
    public static final String ROLE_TENANT_ADMIN = "tenant_admin";

    /**
     * 授权对象为用户
     */
    public static final String AUTHORIZE_TYPE_USER = "0";

    /**
     * 授权对象为租户
     */
    public static final String AUTHORIZE_TYPE_TENANT = "1";

    /**
     * 用户状态，1：正常，0：禁用，-1锁定
     */
    public static final String USER_STATUS_ENABLED = "1";
    public static final String USER_STATUS_DISABLED = "0";
    public static final String USER_STATUS_LOCKED = "-1";

    public static final Integer TENANT_STATUS_ENABLED = 1;
    public static final Integer TENANT_STATUS_DISABLE = 0;

    public static final String SOURCE_SYSTEM_SYS = "sys";
    public static final String SOURCE_SYSTEM_BIZ = "biz";

    public static final int FAIL_LOCK_TIME = 5;
    public static Integer INClUDE_SELF = 1;
    public static Integer EXClUDE_SELF = 0;

    /**
     * 订单状态：0-已关闭 1-已完成 2-处理中 3-待处理
     */
    public static final int ORDER_STATUS_CLOSED = 0;
    public static final int ORDER_STATUS_FINISHED = 1;
    public static final int ORDER_STATUS_PROCESSING = 2;
    public static final int ORDER_STATUS_PENDING = 3;

    /**
     * 平台类型：OPT001-线下 OPT002-飞猪 OPT003-美团 OPT004-携程 OPT005-马蜂窝
     */
    public static final String PLATFORM_LOCAL = "OPT001";
    public static final String PLATFORM_FEIZHU = "OPT002";
    public static final String PLATFORM_MEITUAN = "OPT003";
    public static final String PLATFORM_XIECHENG = "OPT004";
    public static final String PLATFORM_MAFENGWO = "OPT005";

    /**
     * 订单来源：OTA-第三方平台订单 LO-本地订单
     */
    public static final String ORDER_SOURCE_OTA = "OTA";
    public static final String ORDER_SOURCE_LOCAL = "LO";

    public static final Integer SETTLE_FINISH = 1;
    public static final Integer NOT_SETTLE_FINISH = 0;

    /**
     * 产品类型
     */
    public static final String TICKET_GOODS = "票务";
    public static final String HOTEL_GOODS = "酒店";

    /**
     * 已接入OTA平台
     */
    public static final String PLATFORM_MT = "美团";
    public static final String PLATFORM_FZ = "飞猪";
    public static final String PLATFORM_MFW = "马蜂窝";

    /**
     * 库存类型 0-非包票 1-包票
     */
    public static final Integer STOCK_TYPE_FBP = 0;
    public static final Integer STOCK_TYPE_BP = 1;
}
