package com.caosoft.hyper.api.server.service;


import com.caosoft.hyper.api.server.po.ApiKey;

/**
 * **************************************************************
 * Copyright (c) 2013-2018 扬州鲜生活电子商务有限公司
 * All rights reserved
 *
 * @author 曹梦龙 <138888611@qq.com>
 * @name IApiKeyService
 * @describe AppKey业务
 * @create 2018-03-10 11:31
 * **************************************************************
 */
public interface IApiKeyService {
    public ApiKey getApiKeyByKey(String appkey);
}
