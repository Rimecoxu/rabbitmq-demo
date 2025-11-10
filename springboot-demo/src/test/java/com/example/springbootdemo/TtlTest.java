package com.example.springbootdemo;

import com.example.springbootdemo.config.RabbitMqConfig;
import com.example.springbootdemo.ttl.Provider5;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: Rimecoxu@gmail.com
 * @CreateTime: 2025-11-10 22:09
 * @Description: 测试消息TTL
 */
@Slf4j
@SpringBootTest
public class TtlTest {

    @Resource
    private Provider5 provider5;

    @Test
    public void process() {
        provider5.sendMessage("TTL Msg", RabbitMqConfig.SP_DIRECT_ROUTING_KEY);
    }
}
