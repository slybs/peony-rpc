package com.lege.democlient.controller;

import com.lege.demoserverapi.SendMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @Author 了个
 * @date 2020/5/26 19:42
 */
@RestController
@RequestMapping("/testdemo")
@CrossOrigin(origins = "*", allowCredentials = "true") // 允许所有来源，允许发送 Cookie
public class TestDemoController {
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private SendMessageService sendMessageService;
    /**
     * 01.测试接口
     * @param httpServletRequest
     * @return
     * http://localhost:8888/legerpctest/testdemo/test
     */
    @RequestMapping("/test")
    public CommonResult test(HttpServletRequest httpServletRequest){
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        System.out.println(Arrays.asList(beanDefinitionNames));
        String tenglegeStr = sendMessageService.sendName("tenglege");
        return ResultUtil.success(tenglegeStr);
    }
}
