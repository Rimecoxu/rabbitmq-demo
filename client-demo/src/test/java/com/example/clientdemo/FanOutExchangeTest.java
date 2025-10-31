package com.example.clientdemo;

import com.example.clientdemo.util.MQSupportUtil;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Delivery;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @Author: Rimecoxu@gmail.com
 * @CreateTime: 2025-10-31 20:34
 * @Description: 扇出交换机测试: 绑定过的队列都会收到消息
 */
@Slf4j
public class FanOutExchangeTest {
    // 交换机名称
    private static final String EXCHANGE_NAME = "fan_out_exchange";

    // 队列名称
    private static final String QUEUE_NAME_1 = "fan_out_queue_1";
    private static final String QUEUE_NAME_2 = "fan_out_queue_2";
    private static final String QUEUE_NAME_3 = "fan_out_queue_3";


    /**
     * @description : 测试扇出交换机
     */
    @Test
    public void testFanOutProvider() throws IOException {
        // 获取连接
        Connection connection = MQSupportUtil.getConnection();
        // 创建通道
        Channel channel = connection.createChannel();
        // 创建交换机: FANOUT
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT, true, false, null);

        // 创建队列
        channel.queueDeclare(QUEUE_NAME_1, true, false, false, null);
        channel.queueDeclare(QUEUE_NAME_2, true, false, false, null);
        channel.queueDeclare(QUEUE_NAME_3, true, false, false, null);

        // 绑定关系: 1号队列和3号队列都绑定ROUTING_KEY_1, 2号队列绑定ROUTING_KEY_2
        channel.queueBind(QUEUE_NAME_1, EXCHANGE_NAME, "");
        channel.queueBind(QUEUE_NAME_2, EXCHANGE_NAME, "");
        channel.queueBind(QUEUE_NAME_3, EXCHANGE_NAME, "");

        // 发送消息
        String message = "Hello World RabbitMQ-" + System.currentTimeMillis();
        channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());

        log.info("生产者发送消息: {}", message);

        // 关闭通道和连接
        MQSupportUtil.closeChannelAndConnection(channel, connection);
    }

    /**
     * @throws IOException
     * @description : 测试消费者
     */
    @Test
    public void testFanOutConsumer() throws IOException {
        // 获取连接
        Connection connection = MQSupportUtil.getConnection();
        // 创建通道
        Channel channel = connection.createChannel();

        // 接受消息回调
        DeliverCallback deliverCallback = new DeliverCallback() {
            @Override
            public void handle(String consumerTag, Delivery message) {
                log.info("消费者1号, 接收到消息: {}", new String(message.getBody()));
            }
        };

        // 取消消息回调
        CancelCallback cancelCallback = new CancelCallback() {
            @Override
            public void handle(String consumerTag) {
                log.info("消费者1号取消消费回调: {}", consumerTag);
            }
        };

        // 消费消息
        channel.basicConsume(QUEUE_NAME_1, true, deliverCallback, cancelCallback);
        channel.basicConsume(QUEUE_NAME_2, true, deliverCallback, cancelCallback);
        channel.basicConsume(QUEUE_NAME_3, true, deliverCallback, cancelCallback);
    }
}
