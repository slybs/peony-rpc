package com.lege.demo.client.config;

import com.lege.peonycore.serviceregisterdiscover.ServiceRegister;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZKConfig {
    @Value("${zookeeper.url}")
    private String zkurl;
    @Value("${zookeeper.register.path.prefix}")
    private String pathprefix;

    @Bean
    public ServiceRegister getServiceRegister() {
        ServiceRegister serviceRegister = new ServiceRegister(zkurl, pathprefix);
        return serviceRegister;
    }
}
