package com.qg.springboot.controller;

import com.qg.springboot.limit.RequestLimit;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author:Wilder Gao
 * @time:2018/4/3
 * @Discription：
 */
@Controller
@RequestMapping("/limit")
public class LimitController {

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    @RequestLimit
    @ResponseBody
    public String limitTest(HttpServletRequest request){
        System.out.println("用户"+request.getRequestedSessionId()+"访问了......");
        return "connect Success";
    }
}
