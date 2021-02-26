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

/**
 * @ClassName: Scope
 * @Description: TODO
 * @Author: xueyanping
 * @date: 2021/2/26 10:46
 */
@Data
@Accessors(chain = true)
@TableName("oauth_client_scope")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Scope implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    /**
     * 客户端ID
     */
    @TableField("client_id")
    private String clientId;

    /**
     * 授权范围
     */
    private String scope;

    @TableField("auto_approve")
    private Boolean autoApprove;


}

