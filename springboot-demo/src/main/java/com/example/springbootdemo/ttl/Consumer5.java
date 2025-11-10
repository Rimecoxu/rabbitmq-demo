package com.example.springbootdemo.ttl;

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
 * @Description: ttl消息消费
 */
@Slf4j
@Component
public class Consumer5 {

    @RabbitListener(queues = RabbitMqConfig.SP_DIRECT_QUEUE_NAME, ackMode = "MANUAL")
    public void receive(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag) throws IOException {
        try {
            log.info("消费者5号, 接收到消息: {}", message);
            channel.basicAck(deliveryTag, false);
        } catch (Exception e) {
            log.error("消费者5号, 处理消息失败: {}", message);
            channel.basicNack(deliveryTag, false, true);
        }
    }
}
