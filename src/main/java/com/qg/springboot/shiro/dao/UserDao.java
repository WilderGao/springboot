package com.qg.springboot.shiro.dao;


import com.qg.springboot.shiro.model.Permission;
import com.qg.springboot.shiro.model.Role;
import com.qg.springboot.shiro.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author WilderGao
 * time 2018-12-04 20:49
 * motto : everything is no in vain
 * description
 */
@Repository
@Mapper
public interface UserDao {

    User selectUserById(int id);

    /**
     * 通过账号获取用户信息
     * @param account  账号
     * @return  用户信息
     */
    User getUserByAccount(@Param("account") String account);

    /**
     * 通过userId获取角色信息
     * @param userId 用户Id
     * @return 用户对应角色集合
     */
    List<Role> getRolesByUserId(@Param("userId") int userId);

    /**
     * 通过角色id来获取对应的权限
     * @param roleId 角色Id
     * @return 权限集合
     */
    List<Permission> getPermissionByRoleId(@Param("roleId") int roleId);

    /**
     * 获取所有用户信息
     * @return 角色信息
     */
    List<User> getAllMembers();

    /**
     * 添加新用户
     * @param user  用户信息
     * @return  添加结果
     */
    int addUser(@Param("u") User user);


}
