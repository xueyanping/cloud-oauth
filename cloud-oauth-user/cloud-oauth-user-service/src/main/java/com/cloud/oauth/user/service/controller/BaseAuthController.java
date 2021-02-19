package com.cloud.oauth.user.service.controller;

import com.cloud.oauth.framework.base.model.UserAuthentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xueyanping
 * @since 2021/2/16 16:06
 * 用户授权接口
 **/
@RestController
@RequestMapping("/auth")
public class BaseAuthController {

    /**
     * 通过用户名查询用户
     *
     * @param username 用户名
     * @return UserAuthentication 对象
     */
    @GetMapping("/findUserByLoginKey")
    public UserAuthentication findUserByLoginKey(@RequestParam("username") String username) {
        String password = "{bcrypt}$2a$10$Y1mqtU0XZI.MXLAxYLdI2OSrnpq4fBahDQjfDfkLh8g.nTnWHymyi";
        UserAuthentication authentication = new UserAuthentication();
        authentication.setNickName(username);
        authentication.setLoginName(username);
        authentication.setEmail("1564433435@qq.com");
        authentication.setPassword(password);
        authentication.setMobileNumber(username);
        authentication.setLocked(0);
        authentication.setStatus(1);
        authentication.setId("82390482093840923");
        authentication.setSourceSystem("biz");
        authentication.setLoginFailTime(0);
        authentication.setLastLoginIp("qweqweqw");
        return authentication;
    }
}
