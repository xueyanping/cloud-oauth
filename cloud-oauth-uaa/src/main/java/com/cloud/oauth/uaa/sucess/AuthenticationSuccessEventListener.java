package com.cloud.oauth.uaa.sucess;


import com.cloud.oauth.framework.base.model.User;
import com.cloud.oauth.user.BaseUserClient;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;


@Component
public class AuthenticationSuccessEventListener implements ApplicationListener<AuthenticationSuccessEvent> {
    public static final Log logger = LogFactory.getLog(AuthenticationSuccessEventListener.class);

//    private final SysLogService sysLogService;

    @Autowired
    BaseUserClient baseUserClient;

//    public AuthenticationSuccessEventListener(BaseUserClient baseUserClient) {
//        this.baseUserClient = baseUserClient;
//    }
//    final
//    SysUserClient sysUserClient;

//    public AuthenticationSuccessEventListener(SysLogService sysLogService, BaseUserClient baseUserClient, SysUserClient sysUserClient) {
//        this.sysLogService = sysLogService;
//        this.baseUserClient = baseUserClient;
//        this.sysUserClient = sysUserClient;
//    }

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        if (!event.getSource().getClass().getName().equals("org.springframework.security.authentication.UsernamePasswordAuthenticationToken")) {
            return;
        }
        if (event.getAuthentication() == null || event.getAuthentication().getPrincipal() == null ||
                !(event.getAuthentication().getPrincipal() instanceof User)) {
            return;
        }

        User user = (User) event.getAuthentication().getPrincipal();

//        updateLoginInfo(user);

//        String companyId = resetDefaultCompany(user);

//        buildSysLog(user, companyId);

//        try{
//            sysUserClient.userLogin(user.getId(),companyId);
//        }catch (Exception e){
//            logger.error(e.getMessage(),e);
//        }
    }

//    private void updateLoginInfo(User user) {
//        try {
//            if (CommonConstant.SOURCE_SYSTEM_SYS.equals(user.getSourceSystem())) {
//                this.sysUserClient.updateUserLoginInfo(user.getId(), WebUtils.getClientIp(), 1);
//            } else {
//                this.baseUserClient.updateUserLoginInfo(user.getId(), WebUtils.getClientIp(), 1);
//            }
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//        }
//    }

//    private String resetDefaultCompany(User user) {
//        String companyId = "none";
//        try {
//            if (CommonConstant.SOURCE_SYSTEM_SYS.equals(user.getSourceSystem())) {
//                companyId = CommonConstant.SOURCE_SYSTEM_SYS;
//            } else {
//                Result<String> result = this.baseUserClient.switchCurrentCompanyId(user.getId(), "");
//                if (result.getSuccess()) {
//                    companyId = result.getData();
//                }
//            }
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//        }
//        return companyId;
//    }

//    private void buildSysLog(User user, String companyId) {
//        try {
//            SysLogEntity sysLog = new SysLogEntity();
//            //注解上的描述
//            sysLog.setBizName("用户登录");
//            sysLog.setOpName("登录成功");
//            sysLog.setBizMethod("UserNamePasswordAuthenticator.authenticate()");
//            //请求的参数
//            sysLog.setArgs(StringUtils.isEmpty(user.getLoginName()) ? user.getMobileNumber() : user.getLoginName());
//            // 设置操作人
//            sysLog.setCompanyId(companyId);
//            sysLog.setOpBy(StringUtils.isEmpty(user.getLoginName()) ? user.getMobileNumber() : user.getLoginName());
//            sysLog.setOpUserName(user.getNickName());
//            //设置IP地址
//            sysLog.setOpIp(WebUtils.getClientIp());
//
//            sysLog.setOpTime(new Date());
//
//            sysLog.setResponseTime(0);
//            sysLog.setSuccess(true);
//            //保存系统日志
//            sysLogService.insert(sysLog);
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//        }
//    }
}
