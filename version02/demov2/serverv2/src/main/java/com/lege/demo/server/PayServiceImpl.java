package com.lege.demo.server;




import com.apiv2.PayService;
import com.lege.peonycore.common.PeonyRpcService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author tenglege
 * @create 2019/10/30 11:42
 * @Version 0.1
 */
@Slf4j
@PeonyRpcService(PayService.class)
public class PayServiceImpl implements PayService {
    @Override
    public int calculate(int a, int b) {
        int result = a + b;
        return result;
    }
}
