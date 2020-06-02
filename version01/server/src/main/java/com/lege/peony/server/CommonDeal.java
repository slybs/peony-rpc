package com.lege.peony.server;

/**
 * @Author 了个
 * @date 2020/5/26 19:03
 */

import com.alibaba.fastjson.JSON;
import com.lege.peony.common.RequestMethod;
import com.lege.peony.common.ResponseResult;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

//import com.lege.demo.server.configuration.InitRpcConfig;

public class CommonDeal {

    public static String getInvokeMethodMes(String str){
        RequestMethod request = JSON.parseObject(str, RequestMethod.class);
        return JSON.toJSONString(invokeMethod(request));
    }

    private static ResponseResult invokeMethod(RequestMethod request) {
        String className = request.getClassName();
        String methodName = request.getMethodName();
        Object[] parameters = request.getParameters();
        Class<?>[] parameTypes = request.getParameTypes();
        Object o = ContextStartedEventListener.rpcServiceMap.get(className);
        ResponseResult response = new ResponseResult();
        try {
            Method method = o.getClass().getDeclaredMethod(methodName, parameTypes);
            Object invokeMethodResult = method.invoke(o, parameters);
            response.setResult(invokeMethodResult);
        } catch (NoSuchMethodException e) {
            System.out.println("没有找到" + methodName);
        } catch (IllegalAccessException e) {
            System.out.println("执行错误" + parameters);
        } catch (InvocationTargetException e) {
            System.out.println("执行错误" + parameters);
        }
        return response;
    }
}
