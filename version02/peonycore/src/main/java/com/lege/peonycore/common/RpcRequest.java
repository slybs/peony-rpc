package com.lege.peonycore.common;

import lombok.Data;

/**
 * 封装 RPC 请求 封装发送的object的反射属性
 * @Author 了个
 */
@Data
public class RpcRequest {
    private String requestId;
    private String className;
    private String methodName;
    private Class<?>[] parameterTypes;
    private Object[] parameters;
}
