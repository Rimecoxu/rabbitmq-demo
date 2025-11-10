package com.example.springbootdemo.prefetch;

import com.example.springbootdemo.config.RabbitMqConfig;
import com.rabbitmq.client.Channel;
import java.io.IOException;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

/**
 * @Author: Rimecoxu@gmail.com
 * @CreateTime: 2025-11-10 18:39
 * @Description: 消费端限流-消息预读机制
 */
@Slf4j
@Component
public class Consumer4 {

    String[] messages = {"Hello World RabbitMQ-1", "Hello World RabbitMQ-2", "Hello World RabbitMQ-3", "Hello World RabbitMQ-4"};

    @RabbitListener(queues = RabbitMqConfig.SP_DIRECT_QUEUE_NAME, ackMode = "MANUAL")
    public void receive(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag) throws IOException {
        try {
            log.info("消费者4号, 接收到消息: {}", message);
            // 优先处理前4条消息，然后再处理10条消息，一共14条消息。
            if (Arrays.asList(messages).contains(message)) {
                channel.basicAck(deliveryTag, false);
            }
        } catch (Exception e) {
            log.error("消费者4号, 处理消息失败: {}", message);
            channel.basicNack(deliveryTag, false, true);
        }
    }
}
