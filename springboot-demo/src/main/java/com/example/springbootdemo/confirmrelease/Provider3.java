package com.example.springbootdemo.confirmrelease;

import com.example.springbootdemo.config.DirectRabbitMqConfig;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * @Author: Rimecoxu@gmail.com
 * @CreateTime: 2025-11-05 18:28
 * @Description: 确认发布
 */
@Slf4j
@Component
public class Provider3 {

    @Resource
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(String message, String routingKey, CorrelationData correlationData) {
        log.info("生产者3号发送消息: {}", message);
        rabbitTemplate.convertAndSend(DirectRabbitMqConfig.SP_DIRECT_EXCHANGE_NAME, routingKey, message, correlationData);
    }
}
