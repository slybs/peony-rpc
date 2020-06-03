package com.lege.demo.client.service.impl;



import com.apiv2.DemoService;
import com.apiv2.PayService;
import com.lege.demo.client.service.OrderService;

import com.lege.peonycore.common.RpcClientService;
import lombok.extern.slf4j.Slf4j;

import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Set;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private PayService payService;
    @Autowired
    private DemoService demoService;
    @Autowired
    private ApplicationContext applicationContext;
    @Override
    public void order() {
        log.info("开启订单");
        int result = payService.calculate(100, 200);
        String ttttttt = demoService.getDemo("ttttttt");
        log.info("ttttttt ===>{}", ttttttt);
        log.info("此订单需要支付金额{}元", result);
        log.info("关闭订单");
    }

    public static void main(String[] args) {
        Set<Class<?>> typesAnnotatedWith = new Reflections("com.lege.api").getTypesAnnotatedWith(RpcClientService.class);
        typesAnnotatedWith.forEach(System.out::println);
    }
}
