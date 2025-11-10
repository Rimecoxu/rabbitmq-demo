package com.example.springbootdemo;

import com.example.springbootdemo.ackmode.Provider1;
import com.example.springbootdemo.config.RabbitMqConfig;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: Rimecoxu@gmail.com
 * @CreateTime: 2025-11-05 00:16
 * @Description: 消息应答模式测试
 */
@Slf4j
@SpringBootTest
public class AckModeTest {

    @Resource
    private Provider1 provider1;

    @Test
    void process() {
        for (int i = 1; i <= 3; i++) {
            provider1.sendMessage("Message-" + i, RabbitMqConfig.SP_DIRECT_ROUTING_KEY);
        }
    }
}
