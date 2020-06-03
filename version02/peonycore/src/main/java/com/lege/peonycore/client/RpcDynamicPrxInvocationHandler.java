package com.lege.peonycore.client;

import com.lege.peonycore.common.RpcRequest;
import com.lege.peonycore.common.RpcResponse;
import com.lege.peonycore.serviceregisterdiscover.ServiceDiscovery;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.UUID;

/**
 * @Author 了个
 * @date 2020/6/2 18:58
 */
public class RpcDynamicPrxInvocationHandler implements InvocationHandler {
    private String serverAddress;
    private ServiceDiscovery serviceDiscovery;
    private PeonyRpcClient rpcClient;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (Object.class == method.getDeclaringClass()) {
            String name = method.getName();
            if ("equals".equals(name)) {
                return proxy == args[0];
            } else if ("hashCode".equals(name)) {
                return System.identityHashCode(proxy);
            } else if ("toString".equals(name)) {
                return proxy.getClass().getName() + "@" +
                        Integer.toHexString(System.identityHashCode(proxy)) +
                        ", with InvocationHandler " + this;
            } else {
                throw new IllegalStateException(String.valueOf(method));
            }
        }
        //创建RpcRequest，封装被代理类的属性
        RpcRequest request = new RpcRequest();
        request.setRequestId(UUID.randomUUID().toString());
        //拿到声明这个方法的业务接口名称
        request.setClassName(method.getDeclaringClass()
                .getName());
        request.setMethodName(method.getName());
        request.setParameterTypes(method.getParameterTypes());
        request.setParameters(args);
        synchronized (this) {
            if (rpcClient == null) {
                fistBuildClient();
            }
        }
        //通过netty向服务端发送请求
        RpcResponse response = rpcClient.send(request);
        //返回信息
        if (response.isError()) {
            throw response.getError();
        } else {
            return response.getResult();
        }
    }

    private void fistBuildClient() {
        //查找服务
        if(serviceDiscovery == null){
            serviceDiscovery = buildServiceDiscovery();
        }
        if (serviceDiscovery != null) {
            serverAddress = serviceDiscovery.discover();
        }
        //随机获取服务的地址
        String[] array = serverAddress.split(":");
        String host = array[0];
        int port = Integer.parseInt(array[1]);
        //创建Netty实现的RpcClient，链接服务端
        rpcClient = new PeonyRpcClient(host, port);
    }

    private ServiceDiscovery buildServiceDiscovery() {
        Properties properties = new Properties();
        InputStream is=this.getClass().getResourceAsStream("/application.properties");
        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String registerAddress = properties.getProperty("zookeeper.url");
        String dataPath = properties.getProperty("zookeeper.register.path.prefix");

        ServiceDiscovery serviceDiscovery = new ServiceDiscovery(registerAddress, dataPath);
        return serviceDiscovery;
    }
}
