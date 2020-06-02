package com.lege.democlient.controller;

/**
 * @author tenglege
 * @description 统一接口返回值
 * @date 2020/3/14 12:43
 */
public class CommonResult<T> {

    /** 错误码. */
    private int returnCode;

    /** 提示信息. */
    private String returnMsg;

    /** 具体的内容. */
    private T data;

    public int getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(int returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    public T getResult() {
        return data;
    }

    public void setResult(T data) {
        this.data = data;
    }
}
