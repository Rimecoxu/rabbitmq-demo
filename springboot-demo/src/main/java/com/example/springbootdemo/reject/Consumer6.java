package com.example.springbootdemo.reject;

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
 * @CreateTime: 2025-11-10 22:58
 * @Description: 消息拒收
 */
@Slf4j
@Component
public class Consumer6 {

    @RabbitListener(queues = RabbitMqConfig.SP_DIRECT_QUEUE_NAME, ackMode = "MANUAL")
    public void receive(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag) throws IOException {
        if (Integer.parseInt(message) % 2 == 0) {
            log.info("消费者6号, 接收消息: {}", message);
            channel.basicAck(deliveryTag, false);
        } else {
            log.error("消费者6号, 拒收消息: {}", message);
            channel.basicReject(deliveryTag, false);
            // channel.basicNack(deliveryTag, false, true);
        }
    }
}
