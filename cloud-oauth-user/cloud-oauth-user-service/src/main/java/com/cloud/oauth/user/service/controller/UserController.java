package com.cloud.oauth.user.service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xueyanping
 * @since 2021/2/15 13:50
 **/
@RestController
public class UserController {

    @GetMapping("test")
    public String test(){
        return "success";
    }
}
