package com.example.springbootdemo.delay;

import com.example.springbootdemo.config.RabbitMqConfig;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author: Rimecoxu@gmail.com
 * @CreateTime: 2025-11-10 18:39
 * @Description: 延迟消息消费方
 */
@Slf4j
@Component
public class Consumer8 {

    @RabbitListener(queues = RabbitMqConfig.SP_DIRECT_QUEUE_NAME)
    public void receive(String message) {
        log.info("消费者8号, 接收到消息: {}", message);
        log.info("消息消费时间: {}", new Date());
    }
}
