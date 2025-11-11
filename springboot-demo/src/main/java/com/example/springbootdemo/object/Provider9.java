package com.example.springbootdemo.object;

import com.example.springbootdemo.config.DirectRabbitMqConfig;
import com.example.springbootdemo.config.RabbitMqConfig;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * @Author: Rimecoxu@gmail.com
 * @CreateTime: 2025-11-10 22:08
 * @Description: 对象消息提供者
 */
@Slf4j
@Component
public class Provider9 {
    @Resource
    private RabbitTemplate rabbitTemplate;

    public <T> void sendMessage(T message) {
        log.info("生产者9号发送消息: {}", message);
        rabbitTemplate.convertAndSend(DirectRabbitMqConfig.SP_DIRECT_EXCHANGE_NAME, RabbitMqConfig.SP_DIRECT_ROUTING_KEY, message);
    }
}
