package com.lege.democlient.clientrpcconfig;

import com.lege.peony.client.RpcConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @Author 了个
 * @date 2020/6/2 11:13
 */
@Configuration
@Import(RpcConfig.class)
public class RpcClientConfig {

}
