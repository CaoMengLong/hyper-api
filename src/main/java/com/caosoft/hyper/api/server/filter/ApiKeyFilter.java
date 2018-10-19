package com.caosoft.hyper.api.server.filter;

import com.alibaba.fastjson.JSONObject;
import com.caosoft.hyper.api.server.po.ApiKey;
import com.caosoft.hyper.api.server.service.impl.ApiKeyServiceImpl;
import com.caosoft.hyper.api.server.starter.po.HyperResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


/**
 * **************************************************************
 * Copyright (c) 1996-2018 CaoSoft.com
 * All rights reserved
 *
 * @author 曹梦龙 <138888611@qq.com>
 * @name AppKeyFilter
 * @describe AppKey接口访问验证过滤器
 * @create 2018/10/16
 * **************************************************************
 */
@Component
@WebFilter(urlPatterns = "/*",filterName = "AppKeyFilter")

public class ApiKeyFilter implements Filter {
    private final Logger logger = LoggerFactory.getLogger(ApiKeyFilter.class);
    @Autowired
    ApiKeyServiceImpl apiKeyService;

    //排除URL列表
    private static final Set<String> ALLOWED_PATHS = Collections.unmodifiableSet(new HashSet<>(
            Arrays.asList("/main/excludefilter", "/logout", "/register")));

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request= (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");

        //获取请求路径
        String path = request.getRequestURI().substring(request.getContextPath().length()).replaceAll("[/]+$", "");
        boolean allowedPath = ALLOWED_PATHS.contains(path); //判断是否在排除URL列表中

        if (allowedPath==true) {
            //在排除URL中，不进行AppKey鉴权
            filterChain.doFilter(request,response);
            return;
        }


        String appkey = request.getParameter("appkey"); //应用的Appkey
        String sign = request.getParameter("sign");  //加密后的签名
        Long timestamp = Long.valueOf(request.getParameter("timestamp")); //请求的时间戳
        String url= request.getRequestURI().toString(); //请求的URL

        if(appkey==null){
            response.setStatus(401);
            HyperResult hyperResult = HyperResult.getInstance();
            hyperResult.setParam(0,-9999,"AppKey 不存在，调用失败",null);
            response.getWriter().write(JSONObject.toJSONString(hyperResult));
            return;
        }

        ApiKey appKey = apiKeyService.getApiKeyByKey(appkey);

        logger.warn("过滤器实现"+appKey.getSecurity_key());
        filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
