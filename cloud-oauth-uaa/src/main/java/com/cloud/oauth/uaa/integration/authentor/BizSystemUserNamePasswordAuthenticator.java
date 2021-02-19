package com.cloud.oauth.uaa.integration.authentor;

import com.cloud.oauth.framework.base.model.UserAuthentication;
import com.cloud.oauth.uaa.integration.IntegrationAuthentication;
import com.cloud.oauth.user.BaseUserClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * 默认登录处理
 *
 * @author zhangzhn
 * @since 2019-06-10
 **/
@Component
@Primary
public class BizSystemUserNamePasswordAuthenticator extends AbstractPreparableIntegrationAuthenticator {

    @Autowired
    private BaseUserClient baseUserClient;

    @Override
    public UserAuthentication authenticate(IntegrationAuthentication integrationAuthentication) {
        UserAuthentication authentication = baseUserClient.findUserByLoginKey(integrationAuthentication.getUsername());
        return authentication;
    }

    @Override
    public void prepare(IntegrationAuthentication integrationAuthentication) {

    }

    @Override
    public boolean support(IntegrationAuthentication integrationAuthentication) {
        return StringUtils.isEmpty(integrationAuthentication.getAuthType());
//        return false;
    }

}
