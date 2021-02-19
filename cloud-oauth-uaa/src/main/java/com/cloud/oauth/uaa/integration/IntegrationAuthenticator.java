package com.cloud.oauth.uaa.integration;


import com.cloud.oauth.framework.base.model.UserAuthentication;

/**
 * @author zhangzhn
 * @since 2019-06-10
 **/
public interface IntegrationAuthenticator {

    /**
     * 处理集成认证
     *
     * @param integrationAuthentication
     * @return
     */
    UserAuthentication authenticate(IntegrationAuthentication integrationAuthentication);


    /**
     * 进行预处理
     *
     * @param integrationAuthentication
     */
    void prepare(IntegrationAuthentication integrationAuthentication);

    /**
     * 判断是否支持集成认证类型
     *
     * @param integrationAuthentication
     * @return
     */
    boolean support(IntegrationAuthentication integrationAuthentication);

    /**
     * 认证结束后执行
     *
     * @param integrationAuthentication
     */
    void complete(IntegrationAuthentication integrationAuthentication);

}
