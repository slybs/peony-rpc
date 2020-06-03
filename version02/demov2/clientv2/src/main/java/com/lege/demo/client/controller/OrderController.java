package com.lege.demo.client.controller;


import com.lege.demo.client.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController("/orderrestfulapi")
public class OrderController {
    @Autowired
    private OrderService orderService;

    /**
     * http://localhost:8090/orderrestfulapi/ordertest
     * @return
     */
    @RequestMapping("/ordertest")
    public String order() {
        orderService.order();
        return "success";
    }

}
