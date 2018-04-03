package com.qg.springboot.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author:Wilder Gao
 * @time:2018/4/3
 * @Discription：
 */
@Configuration
public class RabbitConfig {

    @Bean
    public CachingConnectionFactory connectionFactory(){
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("127.0.0.1",5672);
        connectionFactory.setUsername("wilder");
        connectionFactory.setPassword("jiancheng");
        connectionFactory.setVirtualHost("/");
        connectionFactory.setPublisherConfirms(true);
        return connectionFactory;
    }

    public final static String TOPIC_ONE = "topic.one";
    public final static String TOPIC_TWO = "topic.two";
    public final static String TOPIC_EXCHANGE = "topicExchange";

    @Bean
    public Queue queue_one(){
        Queue queue =  new Queue(TOPIC_ONE, true);
        return queue;
    }

    @Bean
    public Queue queue_two(){
        Queue queue = new Queue(TOPIC_TWO, true);
        return queue;
    }

    @Bean(name = "topicExchange")
    TopicExchange exchange(){
        // topic 类型的交换器可以实现模糊匹配
        TopicExchange topicExchange = new TopicExchange(TOPIC_EXCHANGE);
        return topicExchange;
    }

    /**
     * 将交换机和消息队列绑定起来
     * @param queue_one
     * @param exchange
     * @return
     */
    @Bean
    Binding bindingExchangeOne(Queue queue_one ,  TopicExchange exchange){
        Binding binding =  BindingBuilder.bind(queue_one).to(exchange).with(TOPIC_ONE);
        return binding;
    }

    @Bean
    Binding bindingExchangeTwo(Queue queue_one ,  TopicExchange exchange){
        Binding binding =  BindingBuilder.bind(queue_one).to(exchange).with(TOPIC_TWO);
        return binding;
    }

}
