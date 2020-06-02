package com.lege.demo.server;


import com.apiv2.DemoService;
import com.lege.peonycore.common.PeonyRpcService;

@PeonyRpcService(DemoService.class)
public class DemoServiceImpl implements DemoService{
    @Override
    public String getDemo(String str) {
        return "hello:"+str;
    }
}
