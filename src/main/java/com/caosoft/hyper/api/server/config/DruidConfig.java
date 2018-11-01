package com.caosoft.hyper.api.server.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;


/**
 * **************************************************************
 * Copyright (c) 1996-2018 CaoSoft.com
 * All rights reserved
 *
 * @author 曹梦龙 <138888611@qq.com>
 * @name com.caosoft.hyper.api.server.config
 * @describe describe
 * @create 2018/11/1
 * **************************************************************
 */
@Configuration  //标识该类被纳入spring容器中实例化并管理
public class DruidConfig {
    @Bean
    @ConfigurationProperties(prefix="spring.datasource.druid") //加载时读取指定的配置信息,前缀为spring.datasource.druid
    public DataSource druidDataSource() {
        return new DruidDataSource();
    }
}
