package com.example.springbootdemo.config;

import java.util.HashMap;
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
 * @Description: RabbitMq配置
 */
@Configuration
public class RabbitMqConfig {

    // region 直连交换机
    public static final String SP_DIRECT_QUEUE_NAME = "sp_direct_queue";
    public static final String SP_DIRECT_ROUTING_KEY = "sp_direct_routingKey";
    // endregion

    // region 持久化配置
    public static final String SP_PERSIST_QUEUE_NAME = "sp_persist_queue";
    public static final String SP_PERSIST_ROUTING_KEY = "sp_persist_routingKey";

    // endregion


    // region 死信队列配置

    public static final String SP_NORMAL_QUEUE_NAME = "sp_normal_queue";
    public static final String SP_NORMAL_ROUTING_KEY = "sp_normal_routingKey";

    public static final String SP_DEAD_LETTER_EXCHANGE = "sp_dead_letter_exchange";
    public static final String SP_DEAD_LETTER_QUEUE_NAME = "sp_dead_letter_queue";
    public static final String SP_DEAD_LETTER_ROUTING_KEY = "sp_dead_letter_routingKey";

    // endregion


    // region 创建直连队列和绑定关系

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
    // endregion


    // region 创建持久化队列和绑定关系

    /**
     * 创建队列
     *
     * @return 持久化队列
     */
    @Bean("spPersistQueue")
    public Queue queue2() {
        return QueueBuilder.durable(SP_PERSIST_QUEUE_NAME).build();
        // return QueueBuilder.durable(SP_PERSIST_QUEUE_NAME).lazy().build(); // 返回惰性队列
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
    // endregion

    // region 创建死信队列和绑定关系

    /**
     * 创建死信交换机
     *
     * @return 交换机
     */
    @Bean("spDeadLetterExchange")
    public Exchange exchange() {
        return ExchangeBuilder.directExchange(SP_DEAD_LETTER_EXCHANGE).build();
    }

    /**
     * 创建死信队列
     *
     * @return 死信队列
     */
    @Bean("spDeadLetterQueue")
    public Queue queue3() {
        return QueueBuilder.durable(SP_DEAD_LETTER_QUEUE_NAME).build();
    }

    /**
     * 指定交换机和队列的绑定关系
     *
     * @param exchange 交换机
     * @param queue    队列
     * @return 绑定关系
     */
    @Bean("spDeadLetterBinding")
    public Binding binding3(@Qualifier("spDeadLetterExchange") Exchange exchange, @Qualifier("spDeadLetterQueue") Queue queue) {
        return BindingBuilder.bind(queue).to(exchange).with(SP_DEAD_LETTER_ROUTING_KEY).noargs();
    }

    /**
     * 创建普通队列关联死信队列
     *
     * @return 普通队列
     */
    @Bean("spNormalQueue")
    public Queue queue4() {
        // 设置死信队列关联
        HashMap<String, Object> map = new HashMap<>();
        map.put("x-dead-letter-exchange", SP_DEAD_LETTER_EXCHANGE);
        map.put("x-dead-letter-routing-key", SP_DEAD_LETTER_ROUTING_KEY);
        return QueueBuilder
                .durable(SP_NORMAL_QUEUE_NAME)
                .withArguments(map)
                // 队列消息最大长度
                .maxLength(3)
                .build();
    }

    /**
     * 指定普通交换机和普通队列的绑定关系
     *
     * @param exchange 交换机
     * @param queue    队列
     * @return 绑定关系
     */
    @Bean("spNormalBinding")
    public Binding binding4(@Qualifier("spDirectExchange") Exchange exchange, @Qualifier("spNormalQueue") Queue queue) {
        return BindingBuilder.bind(queue).to(exchange).with(SP_NORMAL_ROUTING_KEY).noargs();
    }
    // endregion

}
