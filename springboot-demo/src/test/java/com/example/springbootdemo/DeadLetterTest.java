package com.example.springbootdemo;

import com.example.springbootdemo.config.RabbitMqConfig;
import com.example.springbootdemo.deadletter.Provider7;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: Rimecoxu@gmail.com
 * @CreateTime: 2025-11-11 00:26
 * @Description: 死信消息测试
 */
@Slf4j
@SpringBootTest
public class DeadLetterTest {

    @Resource(name = "provider7")
    private Provider7 provider;

    @Test
    public void test() {
        for (int i = 1; i <= 10; i++) {
            provider.sendMessage(String.valueOf(i), RabbitMqConfig.SP_NORMAL_ROUTING_KEY);
        }

    }
}
