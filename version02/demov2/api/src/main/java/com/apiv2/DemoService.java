package com.apiv2;


import com.lege.peonycore.common.RpcClientService;

@RpcClientService
public interface DemoService {
    String getDemo(String str);
}
