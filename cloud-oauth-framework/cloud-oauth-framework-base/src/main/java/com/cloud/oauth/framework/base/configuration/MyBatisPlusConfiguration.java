package com.cloud.oauth.framework.base.configuration;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.cloud.oauth.framework.base.mapper.CommonMapper;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MybatisPlus配置
 *
 * @author zhangzhn
 * @since 2019-06-10
 */
@Slf4j
@Configuration
@MapperScan(basePackages = {"com.cloud.oauth.**.mapper"})
public class MyBatisPlusConfiguration {
    /**
     * mybatis-plus分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    @Bean
    public CommonMapper commonMapper() {
        log.info("Init CommonMapper");
        return new CommonMapper();
    }

//    @Bean
//    public LogicSqlInjector logicSqlInjector(){
//        return new LogicSqlInjector() {
//            /**
//             * 注入自定义全局方法
//             */
//            @Override
//            public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
//                List<AbstractMethod> methodList = super.getMethodList(mapperClass);
//                methodList.add(new LogicDeleteByIdWithFill());
//                return methodList;
//            }
//        };
//    }
}
