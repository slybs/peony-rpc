package com.lege.demoserver.rpcconfig;

import com.lege.peony.server.ContextStartedEventListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author 了个
 * @date 2020/6/2 10:56
 */
@Configuration
public class RpcServerConfig {
    @Bean
    public ContextStartedEventListener contextStartedEventListener(){
        return new ContextStartedEventListener();
    }
}
