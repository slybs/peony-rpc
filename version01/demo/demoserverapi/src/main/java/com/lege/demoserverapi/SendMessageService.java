package com.lege.demoserverapi;


import com.lege.peony.common.annotation.RpcClient;

/**
 * @Author 了个
 * @date 2020/5/26 19:06
 */
@RpcClient
public interface SendMessageService {
    public String sendName(String name);
}
