package com.example.clientdemo;

import com.example.clientdemo.util.MQSupportUtil;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Delivery;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @Author: Rimecoxu@gmail.com
 * @CreateTime: 2025-10-31 20:34
 * @Description: 头部交换机测试
 */
@Slf4j
public class HeadersExchangeTest {
    // 交换机名称
    private static final String EXCHANGE_NAME = "headers_exchange";

    // 队列名称
    private static final String QUEUE_NAME_1 = "headers_queue_1";


    /**
     * @description : 测试头部交换机
     */
    @Test
    public void testHeadersProvider() throws IOException {
        // 获取连接
        Connection connection = MQSupportUtil.getConnection();
        // 创建通道
        Channel channel = connection.createChannel();
        // 创建交换机: HEADERS
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.HEADERS, true, false, null);

        // 创建队列
        channel.queueDeclare(QUEUE_NAME_1, true, false, false, null);

        // 发送消息
        Map<String, Object> map = new HashMap<>();
        map.put("key1", "value3");
        map.put("key2", "value2");
        AMQP.BasicProperties props = new AMQP.BasicProperties().builder().headers(map).build();
        String message1 = "Hello World RabbitMQ-" + System.currentTimeMillis();
        channel.basicPublish(EXCHANGE_NAME, "", props, message1.getBytes());

        // 关闭通道和连接
        MQSupportUtil.closeChannelAndConnection(channel, connection);
    }

    /**
     * @throws IOException
     * @description : 测试消费者
     */
    @Test
    public void testHeadersConsumer() throws IOException {
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

        Map<String, Object> map = new HashMap<>();
        map.put("x-match", "all"); // 匹配模式-完全匹配
        // map.put("x-match", "any"); // 匹配模式-部分匹配
        map.put("key1", "value1");
        map.put("key2", "value2");

        // 绑定队列和交换机
        channel.queueBind(QUEUE_NAME_1, EXCHANGE_NAME, "", map);

        // 消费消息
        channel.basicConsume(QUEUE_NAME_1, true, deliverCallback, cancelCallback);

        try {
            Thread.sleep(5000); // 等待2秒
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
