package com.cloud.oauth.uaa.client.service.impl;

import com.cloud.oauth.framework.base.service.impl.BaseServiceImpl;
import com.cloud.oauth.uaa.client.service.ScopeService;
import com.cloud.oauth.uaa.client.entity.Scope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: ScopeServiceImpl
 * @Description: TODO
 * @Author: xueyanping
 * @date: 2021/2/26 10:47
 */
@Service
public class ScopeServiceImpl extends BaseServiceImpl<Scope> implements ScopeService {
    @Autowired
    private ScopeService scopeService;
}
