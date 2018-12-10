package com.qg.springboot.shiro.service;


import com.qg.springboot.shiro.dto.Result;
import com.qg.springboot.shiro.model.User;

import java.util.List;

/**
 * @author WilderGao
 * time 2018-12-05 11:29
 * motto : everything is no in vain
 * description 登录逻辑处理
 */
public interface LoginService {

    /**
     * 通过账号获取用户信息
     * @param account 账户信息
     * @return  用户信息
     */
    User getUserByAccount(String account);

    /**
     * 获取所有的用户信息
     * @return  用户信息集合
     */
    Result<List<User>> getAllInformation();
}
