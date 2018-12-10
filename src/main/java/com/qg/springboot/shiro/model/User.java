package com.qg.springboot.shiro.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author WilderGao
 * time 2018-12-04 20:22
 * motto : everything is no in vain
 * description
 */
@Data
public class User implements Serializable {
    /**
     * 用户Id
     */
    private int userId;
    /**
     * 用户名
     */
    private String name;
    /**
     * 密码
     */
    private String password;
    /**
     * 邮箱
     */
    private String email;

    /**
     * 用户账号
     */
    private String account;

    /**
     * 用户对应的角色
     */
    private List<Role> roles;

}
