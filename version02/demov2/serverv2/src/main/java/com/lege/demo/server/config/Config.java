package com.lege.demo.server.config;



import com.lege.peonycore.server.PeonyRpcServer;
import com.lege.peonycore.serviceregisterdiscover.ServiceRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author tenglege
 * @create 2019/10/30 13:36
 * @Version 0.1
 */
@Configuration
public class Config {
    @Value("${register.address}")
    private String serverAddress;
    @Autowired
    private ServiceRegister serviceRegister;

    @Bean
    public PeonyRpcServer getRpcServer() {
        return new PeonyRpcServer(serverAddress, serviceRegister);
    }
}
