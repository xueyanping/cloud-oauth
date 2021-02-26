package com.cloud.oauth.uaa.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: Client
 * @Description: TODO
 * @Author: xueyanping
 * @date: 2021/2/26 10:45
 */
@Data
@Accessors(chain = true)
@TableName("oauth_client")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.UUID)
    private String id;

    @TableField("created_by")
    private String createdBy;

    @TableField("created_date")
    private Date createdDate;

    @TableField("last_modified_by")
    private String lastModifiedBy;

    @TableField("last_modified_date")
    private Date lastModifiedDate;

    @TableField("access_token_validity_seconds")
    private Integer accessTokenValiditySeconds;

    @TableField("client_id")
    private String clientId;

    @TableField("client_secret")
    private String clientSecret;

    @TableField("refresh_token_validity_seconds")
    private Integer refreshTokenValiditySeconds;

    /**
     * 授权类型
     */
    @TableField("grant_type")
    private String grantType;

    /**
     * 资源ID
     */
    @TableField("resource_ids")
    private String resourceIds;

    /**
     * 跳转URL
     */
    @TableField("redirect_uri")
    private String redirectUri;

    /**
     * 是否启用
     */
    private Boolean enable;


}

