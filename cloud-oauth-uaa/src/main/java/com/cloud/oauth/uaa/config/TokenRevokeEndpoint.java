package com.cloud.oauth.uaa.config;


import com.cloud.oauth.framework.core.protocol.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.oauth2.provider.endpoint.FrameworkEndpoint;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 登出接口
 *
 * @author zhangzhn
 * @since 2019-06-10
 **/
@FrameworkEndpoint
public class TokenRevokeEndpoint {

    @Autowired
    @Qualifier("consumerTokenServices")
    private ConsumerTokenServices tokenServices;

    @DeleteMapping("/oauth/token")
    public Result<String> deleteAccessToken(@RequestParam("access_token") String accessToken) {
        tokenServices.revokeToken(accessToken);
        return Result.buildSuccess("", "");
    }

}
