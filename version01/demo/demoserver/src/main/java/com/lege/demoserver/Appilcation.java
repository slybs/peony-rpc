package com.lege.demoserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * TODO:这里可以改成纯Spring启动的
 * @Author 了个
 * @date 2020/6/2 10:35
 */
//scanBasePackages={"com.lege.demo.server","com.lege.api"}
@SpringBootApplication
public class Appilcation {
    public static void main(String[] args) throws NoSuchMethodException {
        SpringApplication.run(Appilcation.class, args);
    }
}