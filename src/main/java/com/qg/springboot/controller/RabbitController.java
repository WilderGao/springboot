package com.qg.springboot.controller;

import com.qg.springboot.rabbitmq.RabbitSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author:Wilder Gao
 * @time:2018/4/3
 * @Discription：
 */
@Controller
@RequestMapping("/rabbit")
public class RabbitController {
    @Autowired
    private RabbitSender rabbitSender;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public String test(@RequestParam("content") String content){
        System.out.println(content);
        rabbitSender.sendToQueueOne("topicExchange", "topic.one", content);
        return "get";
    }
}
