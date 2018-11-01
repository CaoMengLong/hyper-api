package com.caosoft.hyper.api.server.filter;

import com.alibaba.druid.support.http.WebStatFilter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * **************************************************************
 * Copyright (c) 1996-2018 CaoSoft.com
 * All rights reserved
 *
 * @author 曹梦龙 <138888611@qq.com>
 * @name com.caosoft.hyper.api.server.filter
 * @describe describe
 * @create 2018/11/1
 * **************************************************************
 */

/**
 * Servlet Filter implementation class DruidFilter
 */
@WebFilter(
    filterName="druidWebStatFilter",
    urlPatterns= {"/*"},
    initParams= {
            @WebInitParam(name="exclusions",value="*.js,*.jpg,*.png,*.gif,*.ico,*.css,/druid/*")//配置本过滤器放行的请求后缀
    }
)
public class DruidStatFilter extends WebStatFilter {
}
