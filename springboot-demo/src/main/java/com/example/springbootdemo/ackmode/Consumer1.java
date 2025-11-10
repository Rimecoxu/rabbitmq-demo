package com.example.springbootdemo.ackmode;

import com.example.springbootdemo.config.RabbitMqConfig;
import com.rabbitmq.client.Channel;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;

/**
 * @Author: Rimecoxu@gmail.com
 * @CreateTime: 2025-11-04 22:41
 * @Description: 直连交换机---消费者
 * RabbitListener随SpringBoot启动，启动时监听队列，有消息则调用方法处理消息
 */
@Slf4j
// @Component
public class Consumer1 {

    /**
     * 监听队列，不确认，如果消费消息时出现错误，消息不会回到队列，存在消息丢失的风险
     *
     * @param message
     */
    // @RabbitListener(queues = RabbitMqConfig.SP_DIRECT_QUEUE_NAME, ackMode = "NONE")
    public void receive1(String message) {
        log.info("消费者receive1, 接收到消息: {}", message);

        if (message.equals("Message-3")) {
            int a = 1 / 0;
        }

        log.info("消费者receive1, 开始处理消息: {}", message);
    }

    /**
     * 监听队列,自动确认，如果消费消息时出现错误，消息会回到队列，重新消费
     *
     * @param message
     */
    // @RabbitListener(queues = RabbitMqConfig.SP_DIRECT_QUEUE_NAME, ackMode = "AUTO")
    public void receive2(String message) {
        log.info("消费者receive2, 接收到消息: {}", message);
        if (message.equals("Message-3")) {
            int a = 1 / 0;
        }
        log.info("消费者receive2, 开始处理消息: {}", message);
    }


    /**
     * 监听队列,手动确认，如果消费消息时出现错误，消息会回到队列，但是正常的消息需要手动确认
     *
     * @param message
     */
    @RabbitListener(queues = RabbitMqConfig.SP_DIRECT_QUEUE_NAME, ackMode = "MANUAL")
    public void receive3(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag) throws IOException {
        try {
            log.info("消费者receive3, 接收到消息: {}", message);
            if (message.equals("Message-3")) {
                int a = 1 / 0;
            }
            log.info("消费者receive3, 开始处理消息: {}", message);
            channel.basicAck(deliveryTag, false);
        } catch (Exception e) {
            log.error("消费者receive3, 处理消息失败: {}", message);
            /**
             * 拒绝消息，并重新入队，并再次尝试消费
             */
            channel.basicNack(deliveryTag, false, true);
        }
    }


}
