package com.qg.springboot.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author:Wilder Gao
 * @time:2018/4/3
 * @Discription：
 */
@Component
public class RabbitHandler {
    @Autowired
    private RabbitSender rabbitSender;

    @RabbitListener(queues = "topic.one")
    public void handler(String content){
        System.out.println("队列1接收到信息"+content);
        rabbitSender.resendToQueue("topic.two",content+"  送到topic.two");
    }

    @RabbitListener(queues = "topic.two")
    public void handler2(String content){
        System.out.println("队列2接收到消息:"+content);
//        return content+new Date();
    }
}
