package com.example.springbootdemo.object;

import com.example.springbootdemo.config.RabbitMqConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

/**
 * @Author: Rimecoxu@gmail.com
 * @CreateTime: 2025-11-10 18:39
 * @Description: 对象消息消费方-统一处理
 */
@Slf4j
// @Component
public class Consumer9 {

    @RabbitListener(queues = RabbitMqConfig.SP_DIRECT_QUEUE_NAME)
    public void receive(String message) {
        log.info("消费者9号, 接收到消息: {}", message);
    }
}
