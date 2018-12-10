package com.qg.springboot.shiro.service.impl;

import com.wilder.safe.dao.UserDao;
import com.wilder.safe.model.User;
import com.wilder.safe.service.SignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author WilderGao
 * time 2018-12-04 20:32
 * motto : everything is no in vain
 * description
 */
@Service
@Slf4j
public class SignServiceImpl implements SignService {
    @Autowired
    private UserDao userDao;

    @Override
    public void signHandler(User user) {
        log.info(">>>>> 开始进行注册");
        if (user != null){
            userDao.addUser(user);

        }
    }
}
