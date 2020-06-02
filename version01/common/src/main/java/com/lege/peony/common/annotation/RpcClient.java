package com.lege.peony.common.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @Author 了个
 * @date 2020/5/26 19:12
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface RpcClient {
}
