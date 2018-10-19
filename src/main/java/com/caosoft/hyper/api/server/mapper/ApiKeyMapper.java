package com.caosoft.hyper.api.server.mapper;

import com.caosoft.hyper.api.server.po.ApiKey;

public interface ApiKeyMapper {
    //通过APPKEY查询
    ApiKey selectByAppKey(String appkey);
}