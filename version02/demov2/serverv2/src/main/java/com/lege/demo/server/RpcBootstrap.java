package com.lege.demo.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 用户系统服务端的启动入口
 * 其意义是启动springcontext，从而构造框架中的RpcServer
 * 亦即：将用户系统中所有标注了RpcService注解的业务发布到RpcServer中
 * @author
 *
 */
@SpringBootApplication
public class RpcBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(RpcBootstrap.class, args);
    }
}
