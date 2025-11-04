package com.example.springbootdemo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Rimecoxu@gmail.com
 * @CreateTime: 2025-11-04 22:12
 * @Description: 直连RabbitMq配置
 */
@Configuration
public class DirectRabbitMqConfig {

    public static final String SP_DIRECT_EXCHANGE_NAME = "sp_direct_exchange";
    public static final String SP_DIRECT_QUEUE_NAME_1 = "sp_direct_queue_1";
    public static final String SP_DIRECT_QUEUE_NAME_2 = "sp_direct_queue_2";
    public static final String SP_DIRECT_QUEUE_NAME_3 = "sp_direct_queue_3";
    public static final String SP_DIRECT_ROUTING_KEY_1 = "sp_direct_routingKey_1";
    public static final String SP_DIRECT_ROUTING_KEY_2 = "sp_direct_routingKey_2";

    /**
     * 创建交换机
     *
     * @return 交换机
     */
    @Bean("spDirectExchange")
    public Exchange exchange() {
        return ExchangeBuilder.directExchange(SP_DIRECT_EXCHANGE_NAME).build();
    }

    /**
     * 创建队列
     *
     * @return 队列
     */
    @Bean("spDirectQueue1")
    public Queue queue1() {
        // durable: 持久化
        return QueueBuilder.durable(SP_DIRECT_QUEUE_NAME_1).build();
    }

    /**
     * 创建队列
     *
     * @return 队列
     */
    @Bean("spDirectQueue2")
    public Queue queue2() {
        // durable: 持久化
        return QueueBuilder.durable(SP_DIRECT_QUEUE_NAME_2).build();
    }

    /**
     * 创建队列
     *
     * @return 队列
     */
    @Bean("spDirectQueue3")
    public Queue queue3() {
        // durable: 持久化
        return QueueBuilder.durable(SP_DIRECT_QUEUE_NAME_3).build();
    }

    /**
     * 指定交换机和队列的绑定关系
     *
     * @param exchange 交换机
     * @param queue    队列
     * @return 绑定关系
     */
    @Bean("spDirectBinding1")
    public Binding binding1(@Qualifier("spDirectExchange") Exchange exchange, @Qualifier("spDirectQueue1") Queue queue) {
        return BindingBuilder.bind(queue).to(exchange).with(SP_DIRECT_ROUTING_KEY_1).noargs();
    }

    /**
     * 指定交换机和队列的绑定关系
     *
     * @param exchange 交换机
     * @param queue    队列
     * @return 绑定关系
     */
    @Bean("spDirectBinding2")
    public Binding binding2(@Qualifier("spDirectExchange") Exchange exchange, @Qualifier("spDirectQueue2") Queue queue) {
        return BindingBuilder.bind(queue).to(exchange).with(SP_DIRECT_ROUTING_KEY_2).noargs();
    }

    /**
     * 指定交换机和队列的绑定关系
     *
     * @param exchange 交换机
     * @param queue    队列
     * @return 绑定关系
     */
    @Bean("spDirectBinding3")
    public Binding binding3(@Qualifier("spDirectExchange") Exchange exchange, @Qualifier("spDirectQueue3") Queue queue) {
        return BindingBuilder.bind(queue).to(exchange).with(SP_DIRECT_ROUTING_KEY_1).noargs();
    }

}
