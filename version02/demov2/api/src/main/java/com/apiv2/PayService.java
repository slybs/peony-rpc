package com.apiv2;


import com.lege.peonycore.common.RpcClientService;

@RpcClientService
public interface PayService {
    int calculate(int a, int b);
}
