package com.example.springbootdemo.delay;

import com.example.springbootdemo.config.DirectRabbitMqConfig;
import java.util.Date;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * @Author: Rimecoxu@gmail.com
 * @CreateTime: 2025-11-10 22:08
 * @Description: 延迟消息提供者
 */
@Slf4j
@Component
public class Provider8 {
    @Resource
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(String message, String routingKey) {
        log.info("生产者8号发送消息: {}", message);
        Message msg = MessageBuilder
                .withBody(message.getBytes())
                // 设置延迟消息，即延迟10秒发送到队列
                .setHeader("x-delay", 10000)
                .build();
        log.info("消息发送时间: {}", new Date());
        rabbitTemplate.convertAndSend(DirectRabbitMqConfig.SP_DIRECT_EXCHANGE_NAME, routingKey, msg);
    }
}
