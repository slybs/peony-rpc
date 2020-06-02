package com.lege.democlient.controller;

/**
 * @author tenglege
 * @description 统一接口返回值工具类
 * @date 2020/3/14 12:45
 */
public class ResultUtil {
    public static CommonResult success() {
        return success(null);
    }
    public static CommonResult success(Object object) {
        CommonResult result = new CommonResult();
        result.setReturnCode(200);
        result.setReturnMsg("成功");
        result.setResult(object);
        return result;
    }
    public static CommonResult success(Integer code,Object object) {
        CommonResult result = new CommonResult();
        result.setReturnCode(code);
        result.setReturnMsg("成功");
        result.setResult(object);
        return result;
    }

    public static CommonResult error( String msg) {
        CommonResult result = new CommonResult();
        result.setReturnCode(400);
        result.setReturnMsg(msg);
        return result;
    }
    public static CommonResult error(Integer code, String msg) {
        CommonResult result = new CommonResult();
        result.setReturnCode(code);
        result.setReturnMsg(msg);
        return result;
    }
}
