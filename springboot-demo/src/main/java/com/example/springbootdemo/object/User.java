package com.example.springbootdemo.object;

import java.io.Serializable;
import lombok.Data;

/**
 * @Author: Rimecoxu@gmail.com
 * @CreateTime: 2025-11-11 12:11
 * @Description: 对象消息
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1712804094453759014L;

    private String name;

    private Integer password;
}
