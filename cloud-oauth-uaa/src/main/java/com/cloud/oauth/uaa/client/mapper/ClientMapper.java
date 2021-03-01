package com.cloud.oauth.uaa.client.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cloud.oauth.uaa.client.entity.Client;
import org.springframework.stereotype.Repository;

/**
 * @ClassName: ClientMapper
 * @Description: TODO
 * @Author: xueyanping
 * @date: 2021/3/1 11:01
 */
@Repository
public interface ClientMapper extends BaseMapper<Client> {
}
