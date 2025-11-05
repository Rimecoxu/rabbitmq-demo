package com.example.springbootdemo;

import com.example.springbootdemo.confirmrelease.Provider1;
import java.util.UUID;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: Rimecoxu@gmail.com
 * @CreateTime: 2025-11-05 18:27
 * @Description: 测试消息发布确认
 */
@Slf4j
@SpringBootTest
public class ConfirmReleaseTest {

    @Resource(name = "provider1")
    private Provider1 provider;

    /**
     * @description : 测试发布确认
     */
    @Test
    public void test() {
        String id = UUID.randomUUID().toString();
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(id);
        String message = "Hello World RabbitMQ-" + id;
        // provider.sendMessage(message, RabbitMqConfig.SP_DIRECT_ROUTING_KEY, correlationData);
        // 设置不存在的路由key,模拟消息投递交换机成功，但是投递队列时因为路由key不存在，导致消息投递失败。
        provider.sendMessage(message, "xxxx", correlationData);
    }
}
