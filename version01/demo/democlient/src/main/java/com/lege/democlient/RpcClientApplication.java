package com.lege.democlient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author 了个
 * @date 2020/5/26 19:09
 */
@SpringBootApplication
public class RpcClientApplication {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(RpcClientApplication.class);
        application.run(args);
    }
}
