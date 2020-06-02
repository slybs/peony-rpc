package com.lege.demo.client.config;


import com.lege.peonycore.client.ServiceBeanDefinitionRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceBeanDefinitionRegistryConfig {
    @Bean
    public ServiceBeanDefinitionRegistry getServiceBeanDefinitionRegistry() {
        return new ServiceBeanDefinitionRegistry();
    }
}
