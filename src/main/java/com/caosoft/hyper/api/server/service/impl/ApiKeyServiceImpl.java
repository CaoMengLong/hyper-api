package com.caosoft.hyper.api.server.service.impl;

import com.caosoft.hyper.api.server.filter.ApiKeyFilter;
import com.caosoft.hyper.api.server.mapper.ApiKeyMapper;
import com.caosoft.hyper.api.server.po.ApiKey;
import com.caosoft.hyper.api.server.service.IApiKeyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * **************************************************************
 * Copyright (c) 2013-2018 扬州鲜生活电子商务有限公司
 * All rights reserved
 *
 * @author 曹梦龙 <138888611@qq.com>
 * @name AppKeyServiceImpl
 * @describe 描述它
 * @create 2018-03-10 11:43
 * **************************************************************
 */

@Service
public class ApiKeyServiceImpl implements IApiKeyService {
    private final Logger logger = LoggerFactory.getLogger(ApiKeyServiceImpl.class);
    @Autowired
    private ApiKeyMapper apiKeyMapper;

    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    /**
     * @author 曹梦龙 <138888611@qq.com>
     * @date 2018/10/20 19:07
     * @methodName getApiKeyByKey
     * @describe 通过appkey查询获取ApiKey
     * @param [appkey]
     * @return com.caosoft.hyper.api.server.po.ApiKey
     */
    public ApiKey getApiKeyByKey(String appkey) {

        // 从缓存中获取城市信息
        String key = "ApiKey_" + appkey;
        ValueOperations<String, ApiKey> operations = redisTemplate.opsForValue();
        // 缓存存在
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            ApiKey apiKey = operations.get(key);
            //logger.info("ApiKeyServiceImpl.getApiKeyByKey() : 从缓存中获取了AppKey >> " + apiKey.toString());
            return apiKey;
        }

        //从DB中查询AppKey
        ApiKey apiKey = apiKeyMapper.selectByAppKey(appkey);
        if (apiKey != null) {
            operations.set(key, apiKey, 10, TimeUnit.SECONDS);
            //logger.info("ApiKeyServiceImpl.getApiKeyByKey() : 插入缓存 >> " + apiKey.toString());
            return apiKey;
        } else {
            return null;
        }
    }
}
