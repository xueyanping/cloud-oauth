package com.cloud.oauth.user;

import com.cloud.oauth.framework.base.model.UserAuthentication;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author xueyanping
 * @since 2021/2/16 12:23
 **/
@FeignClient(name = "user-service")
public interface BaseUserClient {

    @GetMapping("/user/auth/findUserByLoginKey")
    UserAuthentication findUserByLoginKey(@RequestParam("username") String username);

}
