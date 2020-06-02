package com.lege.peonycore.common;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * RPC注解
 * @Author 了个
 */
//注解用在接口上
@Target({ ElementType.TYPE })
//VM将在运行期也保留注释，因此可以通过反射机制读取注解的信息
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface RpcClientService {
}
