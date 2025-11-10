package com.example.springbootdemo.persist;

import com.example.springbootdemo.config.DirectRabbitMqConfig;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * @Author: Rimecoxu@gmail.com
 * @CreateTime: 2025-11-05 18:28
 * @Description: 消息持久化
 */
@Slf4j
@Component
public class Provider2 {

    @Resource
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(String message, String routingKey, MessageDeliveryMode messageDeliveryMode) {
        log.info("生产者发送消息: {}", message);
        Message msg = MessageBuilder
                .withBody(message.getBytes())
                // 设置消息发送模式
                .setDeliveryMode(messageDeliveryMode)
                .build();
        rabbitTemplate.convertAndSend(DirectRabbitMqConfig.SP_DIRECT_EXCHANGE_NAME, routingKey, msg);
    }
}
