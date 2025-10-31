package com.example.clientdemo.util;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * MQ支持工具类
 */
public class MQSupportUtil {

    // ConnectionFactory是一个重量级资源,因此无需多次创建
    private static final ConnectionFactory connectionFactory;

    // 设置连接基本信息
    static {
        connectionFactory = new ConnectionFactory();
        connectionFactory.setVirtualHost("vhost-1");
        connectionFactory.setHost("192.168.99.99");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin");
    }

    /**
     * 获取连接
     *
     * @return Connection
     */
    public static Connection getConnection() {
        try {
            return connectionFactory.newConnection();
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("获取连接失败");
    }

    /**
     * 关闭连接和通道
     *
     * @param channel    通道
     * @param connection 连接
     */
    public static void closeChannelAndConnection(Channel channel, Connection connection) {
        if (channel != null) {
            try {
                channel.close();
            } catch (IOException | TimeoutException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
