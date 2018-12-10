package com.qg.springboot.shiro.service.impl;

import com.qg.springboot.shiro.dao.UserDao;
import com.qg.springboot.shiro.dto.Result;
import com.qg.springboot.shiro.enums.StatusEnum;
import com.qg.springboot.shiro.model.Permission;
import com.qg.springboot.shiro.model.Role;
import com.qg.springboot.shiro.model.User;
import com.qg.springboot.shiro.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author WilderGao
 * time 2018-12-05 11:29
 * motto : everything is no in vain
 * description
 */
@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getUserByAccount(String account) {
        if (account != null){
            //获取账户信息
            User user = userDao.getUserByAccount(account);
            if (user != null){
                log.info("查询账户名为: {}",user.getName());
                List<Role> roles = userDao.getRolesByUserId(user.getUserId());
                if (roles != null){
                    for (Role role : roles) {
                        //通过角色表拿到权限信息
                        List<Permission> permissions = userDao.getPermissionByRoleId(role.getRoleId());
                        role.setPermissions(permissions);
                    }
                }
                user.setRoles(roles);
            }
            return user;
        }
        log.error(">>>>>> 账户信息为空: {}");
        return null;
    }

    @Override
    public Result<List<User>> getAllInformation() {
        Result<List<User>> result;
        List<User> users = userDao.getAllMembers();
        if (users == null || users.size() == 0){
            log.error("查询用户有误");
            result = new Result<>(StatusEnum.USER_ERROR.getState(), null);
        }else {
            result = new Result<>(StatusEnum.OK.getState(), users);
        }
        return result;
    }


}
