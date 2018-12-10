package com.qg.springboot.shiro.enums;

import lombok.Getter;
import lombok.Setter;

/**
 * @author WilderGao
 * time 2018-12-05 17:03
 * motto : everything is no in vain
 * description
 */
public enum StatusEnum{
    /**
     * 正常返回
     */
    OK(200, "一切正常"),
    /**
     * 登录有误
     */
    LOGIN_ERROR(100, "服务器发生错误"),

    /**
     * 用户查询异常
     */
    USER_ERROR(150, "用户异常"),

    /**
     * 没有权限
     */
    AUTH_ERROR(250, "没有权限");

    /**
     * 状态码
     */
    @Getter
    @Setter
    private int state;
    /**
     * 状态信息
     */
    @Getter
    @Setter
    private String info;

    StatusEnum(int state, String info) {
        this.state = state;
        this.info = info;
    }
}
