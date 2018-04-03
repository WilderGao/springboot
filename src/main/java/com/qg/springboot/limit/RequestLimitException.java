package com.qg.springboot.limit;

/**
 * @author:Wilder Gao
 * @time:2018/3/6
 * @Discription：定义一些异常
 */
public class RequestLimitException extends Exception {
    private static final long serialVersionUID = 1364225358754654702L;

    public RequestLimitException(){
        super("HTTP请求超出设定的限制");
    }

    public RequestLimitException(String message){
        super(message);
    }
}
