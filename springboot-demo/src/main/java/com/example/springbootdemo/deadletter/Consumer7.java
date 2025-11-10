package com.example.springbootdemo.deadletter;

import com.example.springbootdemo.config.RabbitMqConfig;
import com.rabbitmq.client.Channel;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

/**
 * @Author: Rimecoxu@gmail.com
 * @CreateTime: 2025-11-10 18:39
 * @Description: 消息消费方
 */
@Slf4j
@Component
public class Consumer7 {

    @RabbitListener(queues = RabbitMqConfig.SP_NORMAL_QUEUE_NAME, ackMode = "MANUAL")
    public void receive(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag) throws IOException {
        if (Integer.parseInt(message) % 2 == 0) {
            log.info("消费者7号, 接收到消息: {}", message);
            // channel.basicAck(deliveryTag, false);
        } else {
            log.error("消费者7号, 处理消息失败: {}", message);
            // 消息拒收，且不放回队列，进入死信队列
            channel.basicNack(deliveryTag, false, false);
        }
    }
}
