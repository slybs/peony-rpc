package com.lege.peony.common.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @Author 了个
 * @date 2020/6/2 10:08
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface RpcService {
}
