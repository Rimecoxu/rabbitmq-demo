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


    /**
     * 创建队列
     *
     * @return 队列
     */
    @Bean("spDirectQueue")
    public Queue queue() {
        // durable: 持久化
        return QueueBuilder.durable(SP_DIRECT_QUEUE_NAME).build();
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


}
