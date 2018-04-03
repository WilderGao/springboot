package com.qg.springboot.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author:Wilder Gao
 * @time:2018/4/3
 * @Discription：
 */
@Component
public class RabbitSender {
    //注入AmqpTemplate
    @Autowired
    private AmqpTemplate template;

    /**
     * 将内容发送到对应的消息队列
     * @param exchange  交换机名称
     * @param routeKey  队列名称
     * @param content   内容
     */
    public void sendToQueueOne(String exchange, String routeKey, String content){
        template.convertAndSend(exchange, routeKey, content);
    }

    /**
     * 将处理完的结果返回到消息队列
     * @param queueName
     * @param content
     */
    public void resendToQueue(String queueName, String content){
        template.convertAndSend(queueName, content);
    }
}
