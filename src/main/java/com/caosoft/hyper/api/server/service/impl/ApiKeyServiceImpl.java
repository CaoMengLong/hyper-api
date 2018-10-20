package com.caosoft.hyper.api.server.service.impl;

import com.caosoft.hyper.api.server.mapper.ApiKeyMapper;
import com.caosoft.hyper.api.server.po.ApiKey;
import com.caosoft.hyper.api.server.service.IApiKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    private ApiKeyMapper apiKeyMapper;

    @Override
    public ApiKey getApiKeyByKey(String appkey) {
        return apiKeyMapper.selectByAppKey(appkey);
    }
}
