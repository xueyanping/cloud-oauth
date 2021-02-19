package com.cloud.oauth.framework.base.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zhangzhn
 * @since 2010-10-14
 */
@Data
public class UserAuthentication  implements Serializable {


    private String sourceSystem;

    private String mobileNumber;

    private String id;

    private String loginName;

    private String nickName;

    private String password;

    private String email;

    private Integer status;

    private Integer locked;

    private Integer loginFailTime;

    private String lastLoginIp;
}
