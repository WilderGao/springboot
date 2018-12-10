package com.qg.springboot.shiro.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author WilderGao
 * time 2018-12-05 11:09
 * motto : everything is no in vain
 * description
 */
@Data
public class Role implements Serializable {

    /**
     * 角色Id
     */
    private Integer roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 用户
     */
    private List<User> users;

    /**
     * 角色对应权限集合
     */
    private List<Permission> permissions;
}
