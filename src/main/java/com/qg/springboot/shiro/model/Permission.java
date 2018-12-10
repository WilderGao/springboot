package com.qg.springboot.shiro.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author WilderGao
 * time 2018-12-05 11:13
 * motto : everything is no in vain
 * description
 */
@Data
public class Permission implements Serializable {
    /**
     * 权限Id
     */
    private Integer permissionId;

    /**
     * 权限名称
     */
    private String permissionName;

    /**
     * 角色集合
     */
    private List<Role> roles;

}
