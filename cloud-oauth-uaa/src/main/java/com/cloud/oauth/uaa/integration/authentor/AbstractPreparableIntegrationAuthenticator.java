package com.cloud.oauth.uaa.integration.authentor;


import com.cloud.oauth.framework.base.model.UserAuthentication;
import com.cloud.oauth.uaa.integration.IntegrationAuthentication;
import com.cloud.oauth.uaa.integration.IntegrationAuthenticator;

/**
 * @author zhangzhn
 * @since 2019-06-10
 **/
public abstract class AbstractPreparableIntegrationAuthenticator implements IntegrationAuthenticator {

    @Override
    public abstract UserAuthentication authenticate(IntegrationAuthentication integrationAuthentication);

    @Override
    public abstract void prepare(IntegrationAuthentication integrationAuthentication);

    @Override
    public abstract boolean support(IntegrationAuthentication integrationAuthentication);

    @Override
    public void complete(IntegrationAuthentication integrationAuthentication) {

    }
}
