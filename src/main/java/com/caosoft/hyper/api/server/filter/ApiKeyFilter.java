package com.caosoft.hyper.api.server.filter;


import com.caosoft.hyper.api.server.po.ApiKey;
import com.caosoft.hyper.api.server.service.impl.ApiKeyServiceImpl;
import com.caosoft.hyper.api.starter.po.HyperResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
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

    @Value("${hyper-api.apikey-filter-enable}")
    private Boolean apikeyFilterEnable;

    @Value("${hyper-api.apikey-filter-track}")
    private Boolean apikeyFilterTrack;

    //排除URL列表
    private static final Set<String> ALLOWED_PATHS = Collections.unmodifiableSet(new HashSet<>(
            Arrays.asList("/pay/", "/logout", "/register")));

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request= (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");

        //判刑是否需要执行该过滤器。是否需要进行APIKEY方式鉴权
        if(apikeyFilterEnable==false){
            //不进行AppKey鉴权,直接放行
            filterChain.doFilter(request,response);
            return;
        }

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

        if(appkey==null || sign == null || timestamp== null){
            response.setStatus(401);
            HyperResult hyperResult = HyperResult.getInstance();
            hyperResult.setParam(0,-9999,"请求参数appkey，sign，timestamp 可能不存在，调用失败。",null);
            ObjectMapper mapper = new ObjectMapper();
            response.getWriter().write(mapper.writeValueAsString(hyperResult));
            return;
        }

        ApiKey apiKey = apiKeyService.getApiKeyByKey(appkey);

        if(apiKey == null){
            //数据库中查询不到该APP应用
            response.setStatus(401);
            HyperResult hyperResult = HyperResult.getInstance();
            hyperResult.setParam(0,-9998,"应用 AppKey 不存在，调用失败。",null);
            ObjectMapper mapper = new ObjectMapper();
            response.getWriter().write(mapper.writeValueAsString(hyperResult));
            return;
        }

        String securitykey= apiKey.getSecurity_key(); //获取应用对应的加密密钥
        Long serverTimestamp=System.currentTimeMillis()/1000; //获取服务端的时间，使用10位的时间戳进行比较（秒）

        if(Math.abs(serverTimestamp-timestamp)>30){ //时间差判断如果大于30s 则报错拦截
            response.setStatus(401);
            HyperResult hyperResult = HyperResult.getInstance();
            hyperResult.setParam(0,-9997,"timestamp 请求时间不在服务器允许范围内，调用失败。",null);
            ObjectMapper mapper = new ObjectMapper();
            response.getWriter().write(mapper.writeValueAsString(hyperResult));
            return;
        }

        StringBuilder stringBuilder = new StringBuilder();
        //组织好需要等待加密计算的文本字符串
        String waitSignStr = stringBuilder.append(url).append("?appkey=").append(appkey).append("&timestamp=").append(timestamp.toString()).toString();

        //******加密计算开始*******
        Mac sha256_HMAC = null;
        try {
            sha256_HMAC = Mac.getInstance("HmacSHA256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        //转化密钥
        SecretKeySpec secret_key = new SecretKeySpec(securitykey.getBytes(), "HmacSHA256");
        try {
            //加密初始化
            sha256_HMAC.init(secret_key);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        //加密获得加密后的结果获得服务端的serverSign，准备与请求时传入的sign进行比较
        String serverSign = Base64.encodeBase64String(sha256_HMAC.doFinal(waitSignStr.getBytes()));

        //是否打印APIKEY校验过程关键参数，排除故障时使用。
        if(apikeyFilterTrack){
            logger.info("=============请求签名校对调试数据==============");
            logger.info(String.format("send %s request to %s",request.getMethod(),request.getRequestURI().toString()));
            logger.info("appkey: "+appkey);
            logger.info("sign: "+sign);
            logger.info("timestamp: "+timestamp.toString());
            logger.info("url: "+url);
            logger.info("waitSignStr: "+waitSignStr);
            logger.info("serverSign: "+serverSign);
            logger.info("==============================================");
        }

        //开始比较服务器生成的Sgin是否和服务器传入的一样
        if(serverSign.equals(sign)==false){
            //签名不一致
            response.setStatus(401);
            HyperResult hyperResult = HyperResult.getInstance();
            hyperResult.setParam(0,-9996,"Sign签名校验失败，调用失败。",null);
            ObjectMapper mapper = new ObjectMapper();
            response.getWriter().write(mapper.writeValueAsString(hyperResult));
            return;
        }

        //服务端sign和传入的sign 比较结果一致，顺利通过校验，允许访问后续业务逻辑，放行
        filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
