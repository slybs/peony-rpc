package com.lege.peony.client;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Proxy;

/**
 * @Author 了个
 * @date 2020/5/26 19:13
 */
public class RpcClinetFactoryBean implements FactoryBean {

    @Autowired
    private RpcDynamicPro rpcDynamicPro;

    private Class<?> classType;

    public RpcClinetFactoryBean(Class<?> classType) {
        this.classType = classType;
    }

    public Object getObject() {
        ClassLoader classLoader = classType.getClassLoader();
        Object object = Proxy.newProxyInstance(classLoader, new Class<?>[] { classType }, rpcDynamicPro);
        return object;
    }

    public Class<?> getObjectType() {
        return this.classType;
    }

    public boolean isSingleton() {
        return false;
    }
}
