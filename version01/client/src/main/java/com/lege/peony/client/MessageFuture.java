package com.lege.peony.client;


import com.lege.peony.common.ResponseResult;

/**
 * @Author 了个
 * @date 2020/5/26 19:11
 */
public class MessageFuture {

    private volatile boolean success = false;
    private ResponseResult response;
    private final Object object = new Object();

    public ResponseResult getMessage(){
        synchronized (object){
            while (!success){
                try {
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return response;
        }
    }

    public void setMessage(ResponseResult response){
        synchronized (object){
            this.response = response;
            this.success = true;
            object.notify();
        }
    }
}