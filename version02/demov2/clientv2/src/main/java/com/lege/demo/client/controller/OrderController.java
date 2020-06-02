package com.lege.demo.client.controller;


import com.lege.demo.client.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author tenglege
 * @create 2019/10/23 14:57
 * @Version 0.1
 */
@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping("/order")
    public String order() {
        orderService.order();
        return "success";
    }

}
