package com.example.springbootdemo.direct;

import com.example.springbootdemo.config.DirectRabbitMqConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

/**
 * @Author: Rimecoxu@gmail.com
 * @CreateTime: 2025-11-04 22:41
 * @Description: 直连交换机---消费者
 */
@Slf4j
// @Component
@RabbitListener(queues = DirectRabbitMqConfig.SP_DIRECT_QUEUE_NAME_1)
public class Consumer1 {

    @RabbitHandler
    public void receive(String message) {
        log.info("消费者1号, 接收到消息: {}", message);
    }

}
