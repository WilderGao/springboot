package com.qg.springboot.shiro.service;

import com.wilder.safe.model.User;

/**
 * @author WilderGao
 * time 2018-12-04 20:31
 * motto : everything is no in vain
 * description 注册逻辑接口
 */

public interface SignService {
    /**
     * 处理要注册的用户信息
     * @param user  用户信息
     */
    void signHandler(User user);
}
