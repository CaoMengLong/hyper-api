package com.caosoft.hyper.api.server.controller;

import com.caosoft.hyper.api.server.starter.po.HyperResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @RequestMapping(value = "/index/demo",method= RequestMethod.GET)
    public HyperResult demo(){
        HyperResult hyperResult = HyperResult.getInstance();
        hyperResult.setParam(0,0,"demo messages",null);
        return hyperResult;
    }
}
