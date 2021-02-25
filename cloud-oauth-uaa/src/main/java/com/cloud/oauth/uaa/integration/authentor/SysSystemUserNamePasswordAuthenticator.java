package com.cloud.oauth.uaa.integration.authentor;


import com.cloud.oauth.framework.base.model.UserAuthentication;
import com.cloud.oauth.uaa.integration.IntegrationAuthentication;
import com.cloud.oauth.user.BaseUserClient;
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
public class SysSystemUserNamePasswordAuthenticator extends AbstractPreparableIntegrationAuthenticator {

    @Autowired
    private BaseUserClient sysUserClient;

    @Override
    public UserAuthentication authenticate(IntegrationAuthentication integrationAuthentication) {
        UserAuthentication sysUserAuthentication = sysUserClient.findUserByLoginKey(integrationAuthentication.getUsername());
        return sysUserAuthentication;
    }

    @Override
    public void prepare(IntegrationAuthentication integrationAuthentication) {

    }

    @Override
    public boolean support(IntegrationAuthentication integrationAuthentication) {
        System.out.println("11111111111111111111111111111111111111111");
        return "sys".equalsIgnoreCase(integrationAuthentication.getAuthType());
    }
}
