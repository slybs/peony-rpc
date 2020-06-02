package com.lege.peony.client;

import com.alibaba.fastjson.JSON;
import com.lege.peony.common.RequestMethod;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author 了个
 * @date 2020/5/26 19:14
 */


public class RpcDynamicPro implements InvocationHandler {

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String requestJson = objectToJson(method, args);
        String returnMsg = "";
        Long threadId = Thread.currentThread().getId();
        NettyClientConnect nettyClientConnect = new NettyClientConnect();
        nettyClientConnect.connect(requestJson,threadId);
        return nettyClientConnect.getResponse(threadId).getResult();
    }

    public String objectToJson(Method method, Object[] args) {
        RequestMethod request = new RequestMethod();
        String methodName = method.getName();
        Class<?>[] parameterTypes = method.getParameterTypes();
        String className = method.getDeclaringClass().getName();
        request.setMethodName(methodName);
        request.setParameTypes(parameterTypes);
        request.setParameters(args);
        request.setClassName(getClassName(className));

        return JSON.toJSONString(request);
    }

    private String getClassName(String beanClassName) {
        String className = beanClassName.substring(beanClassName.lastIndexOf(".") + 1);
        className = className.substring(0, 1).toLowerCase() + className.substring(1);
        return className;
    }
}