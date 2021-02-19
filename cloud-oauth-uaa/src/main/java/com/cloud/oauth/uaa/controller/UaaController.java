package com.cloud.oauth.uaa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author xueyanping
 * @since 2021/2/15 16:06
 **/
@RestController
public class UaaController {
    @GetMapping("uaaTest")
    public String test(){
        return "uaa success";
    }

    @GetMapping("/current")
    public Principal getUser(Principal principal) {
        return principal;
    }
}
