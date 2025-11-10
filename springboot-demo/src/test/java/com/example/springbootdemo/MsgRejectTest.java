package com.example.springbootdemo;

import com.example.springbootdemo.config.RabbitMqConfig;
import com.example.springbootdemo.reject.Provider6;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: Rimecoxu@gmail.com
 * @CreateTime: 2025-11-10 23:00
 * @Description: 测试消息拒绝
 */
@Slf4j
@SpringBootTest
public class MsgRejectTest {

    @Resource(name = "provider6")
    private Provider6 provider;

    @Test
    public void process() {
        for (int i = 0; i < 10; i++) {
            provider.sendMessage(String.valueOf(i), RabbitMqConfig.SP_DIRECT_ROUTING_KEY);
        }
    }
}
