package com.lege.peony.client;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @Author 了个
 * @date 2020/5/26 19:14
 */


@Configuration
@Import({RpcDynamicPro.class,RpcInitConfig.class})
public class RpcConfig {

}
