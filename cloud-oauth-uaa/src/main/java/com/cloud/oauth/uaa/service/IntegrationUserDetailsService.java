package com.cloud.oauth.uaa.service;


import com.cloud.oauth.framework.base.model.User;
import com.cloud.oauth.framework.base.model.UserAuthentication;
import com.cloud.oauth.uaa.integration.IntegrationAuthentication;
import com.cloud.oauth.uaa.integration.IntegrationAuthenticationContext;
import com.cloud.oauth.uaa.integration.IntegrationAuthenticator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 集成认证用户服务
 *
 * @author zhangzhn
 * @since 2019-06-10
 **/
@Service
public class IntegrationUserDetailsService implements UserDetailsService {

//    public static int LOCK_THRESHOLD = 90 * 24 * 60 * 60 * 1000;


//    private final BaseAuthorizeClient baseAuthorizeClient;
//
//    private final SysAuthorizeClient sysAuthorizeClient;
//
//    private final BaseUserClient userCompanyClient;

    private List<IntegrationAuthenticator> authenticators;

//    public IntegrationUserDetailsService(BaseAuthorizeClient baseAuthorizeClient, SysAuthorizeClient sysAuthorizeClient, BaseUserClient userCompanyClient) {
//        this.baseAuthorizeClient = baseAuthorizeClient;
//        this.sysAuthorizeClient = sysAuthorizeClient;
//        this.userCompanyClient = userCompanyClient;
//    }

    @Autowired(required = false)
    public void setIntegrationAuthenticators(List<IntegrationAuthenticator> authenticators) {
        this.authenticators = authenticators;
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("username=="+username);
        IntegrationAuthentication integrationAuthentication = IntegrationAuthenticationContext.get();
        //判断是否是集成登录
        if (integrationAuthentication == null) {
            integrationAuthentication = new IntegrationAuthentication();
        }
        integrationAuthentication.setUsername(username);
        UserAuthentication userAuthentication = this.authenticate(integrationAuthentication);
        if (userAuthentication == null) {
            throw new UsernameNotFoundException("用户名或密码错误");}
        User user = new User();
        BeanUtils.copyProperties(userAuthentication, user);

        user.setClientIp("");
        user.setSessionId(integrationAuthentication.getSessionId());
        this.setAuthorize(user);
        return user;
    }

    /**
     * 设置授权信息
     *
     * @param user
     */
    public void setAuthorize(User user) {
//        Authorize authorize = user.getSourceSystem().equalsIgnoreCase("sys") ?
//                this.sysAuthorizeClient.getAuthorize(user.getId()) :
//                this.baseAuthorizeClient.getAuthorize(user.getId(), userCompanyClient.getCurrentCompanyId(user.getId()).getData());
        List<String> listRole = new ArrayList<>();
        listRole.add("r1");
        List<String> listResource = new ArrayList<>();
        listResource.add("p1");
        user.setRoles(listRole);
        user.setResources(listResource);
    }

    private UserAuthentication authenticate(IntegrationAuthentication integrationAuthentication) {
        if (this.authenticators != null) {
            for (IntegrationAuthenticator authenticator : authenticators) {
                if (authenticator.support(integrationAuthentication)) {
                    return authenticator.authenticate(integrationAuthentication);
                }
            }
        }
        return null;
    }
}
