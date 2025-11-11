package com.example.springbootdemo.object;

import com.example.springbootdemo.config.RabbitMqConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author: Rimecoxu@gmail.com
 * @CreateTime: 2025-11-10 18:39
 * @Description: 对象消息消费方
 */
@Slf4j
@Component
@RabbitListener(queues = RabbitMqConfig.SP_DIRECT_QUEUE_NAME)
public class Consumer11 {

    @RabbitHandler
    public void receive(User user) {
        log.info("消费者11号, 接收到消息: {}", user);
    }


}
