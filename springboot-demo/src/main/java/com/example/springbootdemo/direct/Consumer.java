package com.example.springbootdemo.direct;

import com.example.springbootdemo.config.DirectRabbitMqConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author: Rimecoxu@gmail.com
 * @CreateTime: 2025-11-04 22:41
 * @Description: 直连交换机---消费者
 * RabbitListener随SpringBoot启动，启动时监听队列，有消息则调用方法处理消息
 */
@Slf4j
@Component
public class Consumer {

    @RabbitListener(queues = DirectRabbitMqConfig.SP_DIRECT_QUEUE_NAME_1)
    public void receive1(String message) {
        log.info("消费者1号, 接收到消息: {}", message);
    }

    @RabbitListener(queues = DirectRabbitMqConfig.SP_DIRECT_QUEUE_NAME_2)
    public void receive2(String message) {
        log.info("消费者2号, 接收到消息: {}", message);
    }

    @RabbitListener(queues = DirectRabbitMqConfig.SP_DIRECT_QUEUE_NAME_3)
    public void receive3(String message) {
        log.info("消费者3号, 接收到消息: {}", message);
    }

}
