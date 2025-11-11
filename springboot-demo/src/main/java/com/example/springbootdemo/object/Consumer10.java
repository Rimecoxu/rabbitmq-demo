package com.example.springbootdemo.object;

import com.example.springbootdemo.config.RabbitMqConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

/**
 * @Author: Rimecoxu@gmail.com
 * @CreateTime: 2025-11-10 18:39
 * @Description: 对象消息消费方-区分消息类型
 */
@Slf4j
// @Component
@RabbitListener(queues = RabbitMqConfig.SP_DIRECT_QUEUE_NAME)
public class Consumer10 {

    @RabbitHandler
    public void receive(String message) {
        log.info("消费者9号[String], 接收到消息: {}", message);
    }

    @RabbitHandler
    public void receive(byte[] message) {
        log.info("消费者9号[Byte], 接收到消息: {}", new String(message));
    }

    @RabbitHandler
    public void receive(Integer message) {
        log.info("消费者9号[Integer], 接收到消息: {}", message);
    }

    @RabbitHandler
    public void receive(Boolean message) {
        log.info("消费者9号[Boolean], 接收到消息: {}", message);
    }

    @RabbitHandler
    public void receive(Character message) {
        log.info("消费者9号[Character], 接收到消息: {}", message);
    }
}
