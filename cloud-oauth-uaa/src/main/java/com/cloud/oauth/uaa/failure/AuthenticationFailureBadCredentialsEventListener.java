package com.cloud.oauth.uaa.failure;


import com.cloud.oauth.framework.base.model.UserAuthentication;
import com.cloud.oauth.framework.core.constant.CommonConstant;
import com.cloud.oauth.user.BaseUserClient;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AuthenticationFailureBadCredentialsEventListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

    public static final Log logger = LogFactory.getLog(AuthenticationFailureBadCredentialsEventListener.class);


    @Autowired
    private BaseUserClient baseUserClient;

//    @Autowired
//    private SysUserClient sysUserClient;

    @Override
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent event) {
        if (!event.getSource().getClass().getName().equals("org.springframework.security.authentication.UsernamePasswordAuthenticationToken")) {
            return;
        }
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) event.getSource();

        if (!(token.getDetails() instanceof Map)) {
            return;
        }

        Map<String, Object> paramMap = (Map<String, Object>) token.getDetails();
        String authType = paramMap.getOrDefault("auth_type", "").toString();
        String userName = paramMap.getOrDefault("username", "").toString();
        if (StringUtils.isEmpty(userName)) {
            return;
        }
        UserAuthentication userAuthentication = findUserByLoginName(userName, authType);
//        updateLoginInfo(userAuthentication, 0);
//        String companyId = getDefaultCompany(userAuthentication);
//        buildSysLog(userAuthentication, userName, companyId);
    }


    private UserAuthentication findUserByLoginName(String userName, String authType) {
        UserAuthentication userAuthentication = null;
        if (CommonConstant.SOURCE_SYSTEM_BIZ.equalsIgnoreCase(authType)) {
            userAuthentication = baseUserClient.findUserByLoginKey(userName);
        } else {
//            userAuthentication = sysUserClient.findUserByLoginKey(userName);
        }
        return userAuthentication;
    }


//    private String getDefaultCompany(UserAuthentication user) {
//        String companyId = "none";
//        try {
//            if (CommonConstant.SOURCE_SYSTEM_SYS.equals(user.getSourceSystem())) {
//                companyId = CommonConstant.SOURCE_SYSTEM_SYS;
//            } else {
//                Result<String> result = this.baseUserClient.getDefaultCompanyId(user.getId());
//                if (result.getSuccess()) {
//                    companyId = result.getData();
//                }
//            }
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//        }
//        return companyId;
//    }


//    private void updateLoginInfo(UserAuthentication user, int flag) {
//        try {
//            if (CommonConstant.SOURCE_SYSTEM_SYS.equals(user.getSourceSystem())) {
//                this.sysUserClient.updateUserLoginInfo(user.getId(), WebUtils.getClientIp(), flag);
//            } else {
//                this.baseUserClient.updateUserLoginInfo(user.getId(), WebUtils.getClientIp(), flag);
//            }
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//        }
//    }

//    private void buildSysLog(UserAuthentication user, String username, String companyId) {
//        try {
//            SysLogEntity sysLog = new SysLogEntity();
//            sysLog.setBizMethod("UserNamePasswordAuthenticator.authenticate()");
//            sysLog.setBizName("用户登录");
//            sysLog.setOpName("登录失败");
//            //请求的参数
//            sysLog.setArgs(username);
//            sysLog.setCompanyId(companyId);
//            // 设置操作人
//            sysLog.setOpBy(user == null ? username : (StringUtils.isEmpty(user.getLoginName()) ? user.getMobileNumber() : user.getLoginName()));
//            sysLog.setOpUserName(user == null ? username : user.getNickName());
//            //设置IP地址
//            sysLog.setOpIp(WebUtils.getClientIp());
//            sysLog.setOpTime(new Date());
//            sysLog.setResponseTime(0);
//            sysLog.setFailCode("401");
//            sysLog.setFailInfo(user == null ? "此用户不存在，非法访问" : "密码错误");
//            sysLog.setSuccess(false);
//            //保存系统日志
//            sysLogService.insert(sysLog);
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//        }
//    }
}
