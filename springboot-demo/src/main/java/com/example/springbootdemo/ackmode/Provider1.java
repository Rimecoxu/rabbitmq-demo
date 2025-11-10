package com.example.springbootdemo.ackmode;

import com.example.springbootdemo.config.DirectRabbitMqConfig;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * @Author: Rimecoxu@gmail.com
 * @CreateTime: 2025-11-04 22:39
 * @Description: 直连交换机--生产者
 */
@Slf4j
@Component
public class Provider1 {

    @Resource
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(String message, String routingKey) {
        log.info("生产者1号发送消息: {}", message);
        rabbitTemplate.convertAndSend(DirectRabbitMqConfig.SP_DIRECT_EXCHANGE_NAME, routingKey, message);
    }

}
