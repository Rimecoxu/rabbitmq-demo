package com.example.springbootdemo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Rimecoxu@gmail.com
 * @CreateTime: 2025-11-04 22:12
 * @Description: RabbitMq配置
 */
@Configuration
public class RabbitMqConfig {

    public static final String SP_DIRECT_QUEUE_NAME = "sp_direct_queue";
    public static final String SP_DIRECT_ROUTING_KEY = "sp_direct_routingKey";
    public static final String SP_PERSIST_QUEUE_NAME = "sp_persist_queue";
    public static final String SP_PERSIST_ROUTING_KEY = "sp_persist_routingKey";


    /**
     * 创建队列
     *
     * @return 队列
     */
    @Bean("spDirectQueue")
    public Queue queue() {
        // durable: 持久化
        return QueueBuilder.durable(SP_DIRECT_QUEUE_NAME).build();
        // return QueueBuilder.durable(SP_DIRECT_QUEUE_NAME).ttl(15000).build(); // 设置队列消息过期时间
    }

    /**
     * 创建队列
     *
     * @return 持久化队列
     */
    @Bean("spPersistQueue")
    public Queue queue2() {
        // return QueueBuilder.durable(SP_PERSIST_QUEUE_NAME).build();
        return QueueBuilder.durable(SP_PERSIST_QUEUE_NAME).lazy().build(); // 返回惰性队列
    }

    /**
     * 指定交换机和队列的绑定关系
     *
     * @param exchange 交换机
     * @param queue    队列
     * @return 绑定关系
     */
    @Bean("spDirectBinding")
    public Binding binding(@Qualifier("spDirectExchange") Exchange exchange, @Qualifier("spDirectQueue") Queue queue) {
        return BindingBuilder.bind(queue).to(exchange).with(SP_DIRECT_ROUTING_KEY).noargs();
    }

    /**
     * 创建持久化队列的绑定关系
     *
     * @param exchange 持久化交换机
     * @param queue    持久化队列
     * @return 绑定关系
     */
    @Bean("spPersistBinding")
    public Binding binding2(@Qualifier("spDirectExchange") Exchange exchange, @Qualifier("spPersistQueue") Queue queue) {
        return BindingBuilder.bind(queue).to(exchange).with(SP_PERSIST_ROUTING_KEY).noargs();
    }


}
