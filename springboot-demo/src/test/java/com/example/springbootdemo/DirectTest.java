package com.example.springbootdemo;

import com.example.springbootdemo.config.DirectRabbitMqConfig;
import com.example.springbootdemo.direct.Provider;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: Rimecoxu@gmail.com
 * @CreateTime: 2025-11-04 23:05
 * @Description: 直连交换机测试
 */
@Slf4j
@SpringBootTest
public class DirectTest {

    @Resource
    private Provider provider;

    @Test
    void process() {
        String message = "Hello World RabbitMQ-" + System.currentTimeMillis();
        provider.sendMessage(message, DirectRabbitMqConfig.SP_DIRECT_ROUTING_KEY_1);

        message = "Hello World RabbitMQ-" + System.currentTimeMillis();
        provider.sendMessage(message, DirectRabbitMqConfig.SP_DIRECT_ROUTING_KEY_2);
    }

}
