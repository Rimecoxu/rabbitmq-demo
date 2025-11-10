package com.example.springbootdemo.deadletter;

import com.example.springbootdemo.config.DirectRabbitMqConfig;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * @Author: Rimecoxu@gmail.com
 * @CreateTime: 2025-11-10 22:08
 * @Description: 死信消息提供者
 */
@Slf4j
@Component
public class Provider7 {
    @Resource
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(String message, String routingKey) {
        log.info("生产者7号发送消息: {}", message);
        Message msg = MessageBuilder
                .withBody(message.getBytes())
                // 设置消息过期时间,过期后进入死信队列
                .setExpiration("10000")
                .build();
        rabbitTemplate.convertAndSend(DirectRabbitMqConfig.SP_DIRECT_EXCHANGE_NAME, routingKey, msg);
    }
}
