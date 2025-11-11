package com.example.springbootdemo.object;

import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: Rimecoxu@gmail.com
 * @CreateTime: 2025-11-11 11:54
 * @Description: 对象消息测试
 */
@Slf4j
@SpringBootTest
public class ProviderTest {

    @Resource(name = "provider9")
    private Provider9 provider;

    /**
     * 测试发送普通消息
     */
    @Test
    public void test() {
        provider.sendMessage("你好");
        provider.sendMessage("世界".getBytes());
        provider.sendMessage(123456);
        provider.sendMessage(false);
        provider.sendMessage('对');
    }


    /**
     * 测试发送对象消息
     */
    @Test
    public void test2() {
        User user = new User();
        user.setName("admin");
        user.setPassword(123456);
        provider.sendMessage(user);
    }
}
