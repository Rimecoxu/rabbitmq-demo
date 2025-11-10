package com.example.springbootdemo.reject;

import com.example.springbootdemo.config.DirectRabbitMqConfig;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * @Author: Rimecoxu@gmail.com
 * @CreateTime: 2025-11-10 22:08
 * @Description: 消息提供
 */
@Slf4j
@Component
public class Provider6 {
    @Resource
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(String message, String routingKey) {
        log.info("生产者6发送消息: {}", message);
        rabbitTemplate.convertAndSend(DirectRabbitMqConfig.SP_DIRECT_EXCHANGE_NAME, routingKey, message);
    }
}
