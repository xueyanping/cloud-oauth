package com.cloud.oauth.uaa.client.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cloud.oauth.uaa.client.entity.Scope;
import org.springframework.stereotype.Repository;

/**
 * @ClassName: ScopeMapper
 * @Description: TODO
 * @Author: xueyanping
 * @date: 2021/3/1 11:02
 */
@Repository
public interface ScopeMapper extends BaseMapper<Scope> {
}
