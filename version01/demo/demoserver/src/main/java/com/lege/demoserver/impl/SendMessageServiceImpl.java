package com.lege.demoserver.impl;


import com.lege.demoserverapi.SendMessageService;
import com.lege.peony.common.annotation.RpcService;

/**
 * @Author 了个
 * @date 2020/5/26 19:06
 */
@RpcService
public class SendMessageServiceImpl implements SendMessageService {
    public String sendName(String name) {
        return "rpc-server echo: " + name;
    }
}
