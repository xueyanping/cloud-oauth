package com.cloud.oauth.uaa.integration;

import lombok.Data;

import java.util.Map;

/**
 * @author zhangzhn
 * @since 2019-06-10
 **/
@Data
public class IntegrationAuthentication {
    private String authType;
    private String username;
    private String sessionId;
    private Map<String, String[]> authParameters;

    public String getAuthParameter(String parameter) {
        String[] values = this.authParameters.get(parameter);
        if (values != null && values.length > 0) {
            return values[0];
        }
        return null;
    }


}
