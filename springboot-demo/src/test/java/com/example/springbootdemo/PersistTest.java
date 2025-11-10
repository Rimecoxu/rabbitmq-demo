package com.example.springbootdemo;

import com.example.springbootdemo.config.RabbitMqConfig;
import com.example.springbootdemo.persist.Provider2;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: Rimecoxu@gmail.com
 * @CreateTime: 2025-11-10 16:51
 * @Description: 持久化消息测试
 */
@Slf4j
@SpringBootTest
public class PersistTest {

    @Resource(name = "provider2")
    private Provider2 provider;

    /**
     * 测试非持久化消息
     */
    @Test
    void process1() {
        for (int i = 1; i <= 1000000; i++) {
            provider.sendMessage("Non Persistent Message-" + i, RabbitMqConfig.SP_PERSIST_ROUTING_KEY, MessageDeliveryMode.NON_PERSISTENT);
        }
    }

    /**
     * 测试持久化消息
     */
    @Test
    void process2() {
        for (int i = 1; i <= 1000000; i++) {
            provider.sendMessage("Persistent Message-" + i, RabbitMqConfig.SP_PERSIST_ROUTING_KEY, MessageDeliveryMode.PERSISTENT);
        }
    }
}
