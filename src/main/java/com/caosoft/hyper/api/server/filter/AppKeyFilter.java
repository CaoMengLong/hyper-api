package com.caosoft.hyper.api.server.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


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
public class AppKeyFilter implements Filter {
    private final Logger logger = LoggerFactory.getLogger(AppKeyFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        logger.warn("过滤器实现");
        filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
