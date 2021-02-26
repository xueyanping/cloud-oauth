package com.cloud.oauth.framework.base.model;

import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @author zhangzhn
 * @since 2019-06-10
 **/
public class User implements UserDetails, CredentialsContainer, Serializable {

    private String sourceSystem;
    private String id;
    private String loginName;
    private String nickName;
    private String mobileNumber;
    private String email;
    private String password;
    private String clientIp;
    private int locked;
    private int status;
    private int loginFailTime;
    private int lastLoginTime;
    private String lastLoginIp;
    private String sessionId;
    private Collection<String> resources = new ArrayList<>();
    private Collection<String> roles = new ArrayList<>();
    private Collection<SimpleGrantedAuthority> grantedAuthorities;

    @Override
    public Collection<SimpleGrantedAuthority> getAuthorities() {
        if (grantedAuthorities == null) {
            this.grantedAuthorities = this.getRoles().stream().map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList());
        }
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return StringUtils.isEmpty(this.loginName) ? this.getSourceSystem()+"-"+ this.mobileNumber : this.sourceSystem+"-"+this.loginName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.status == 1;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.locked == 0;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Collection<String> getRoles() {
        return roles;
    }

    public void setRoles(Collection<String> roles) {
        this.roles = roles;
    }

    public Collection<String> getResources() {
        return resources;
    }

    public void setResources(Collection<String> resources) {
        this.resources = resources;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void eraseCredentials() {
        this.password = null;
    }

    public int getLocked() {
        return locked;
    }

    public void setLocked(int locked) {
        this.locked = locked;
    }

    public int getLoginFailTime() {
        return loginFailTime;
    }

    public void setLoginFailTime(int loginFailTime) {
        this.loginFailTime = loginFailTime;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }


    public String getSourceSystem() {
        return sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getLoginName(){
        return this.loginName;
    }
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getSessionId(){
        return sessionId;
    }
    public  void setSessionId(String sessionId){
        this.sessionId = sessionId;
    }

    public int getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(int lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
}
