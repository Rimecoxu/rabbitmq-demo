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
 * 客户端测试
 */
@Slf4j
public class ClientDemoTests {

    // 交换机名称
    private static final String EXCHANGE_NAME = "direct_exchange";

    // 队列名称
    private static final String QUEUE_NAME = "direct_queue";

    // 路由key
    private static final String ROUTING_KEY = "direct_routing_key";


    /**
     * @description : 测试直连交换机
     */
    @Test
    public void testDirectProvider() throws IOException {
        // 获取连接
        Connection connection = MQSupportUtil.getConnection();
        // 创建通道
        Channel channel = connection.createChannel();
        // 创建交换机: direct_exchange-直连交换机
        /**
         * Exchange.DeclareOk exchangeDeclare(String exchange, BuiltinExchangeType type, boolean durable, boolean autoDelete,
         *         Map<String, Object> arguments) throws IOException;
         *         参数:
         *         1. 交换机名称
         *         2. 交换机类型
         *         3. 是否持久化
         *         4. 闲置时是否自动删除
         *         5. 其他参数
         */
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT, true, false, null);

        // 创建队列
        /**
         *     Queue.DeclareOk queueDeclare(String queue, boolean durable, boolean exclusive, boolean autoDelete,
         *                                  Map<String, Object> arguments) throws IOException;
         *                                  参数:
         *                                  1. 队列名称
         *                                  2. 是否持久化
         *                                  3. 是否独占
         *                                  4. 是否自动删除
         *                                  5. 其他参数
         */
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);

        // 绑定关系
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY);

        // 消息
        String message = "Hello World RabbitMQ-" + System.currentTimeMillis();

        /**
         * Basic.Publish(String exchange, String routingKey, BasicProperties props, byte[] body) throws IOException;
         * 参数:
         * 1. 交换机, 如果使用默认交换机, 则传入""
         * 2. RoutingKey, 如果使用默认交换机, 则传入队列的名称
         * 3. 其他参数
         * 4. 发送消息的内容
         */
        // 发送消息
        channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, null, message.getBytes());

        // 关闭通道和连接
        MQSupportUtil.closeChannelAndConnection(channel, connection);
    }

    /**
     * @throws IOException
     * @description : 测试消费者
     */
    @Test
    public void testDirectConsumer() throws IOException {
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
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, cancelCallback);
    }

}
