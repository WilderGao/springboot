package com.qg.springboot.shiro;

import com.wilder.safe.model.Role;
import com.wilder.safe.model.User;
import com.wilder.safe.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * @author WilderGao
 * time 2018-12-05 11:03
 * motto : everything is no in vain
 * description
 */
@Slf4j
public class MyShiroRealm extends AuthorizingRealm {
    @Autowired
    @Qualifier(value = "loginServiceImpl")
    private LoginService loginService;

    /**
     * 获取认证成功后的角色、权限等信息
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("进行权限配置 ......");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        User user = (User) principalCollection.getPrimaryPrincipal();
        log.info(">>>>>>>>>>>>>进入授权");
        for (Role role : user.getRoles()) {
            authorizationInfo.addRole(role.getRoleName());
            role.getPermissions().forEach(p->authorizationInfo.addStringPermission(p.getPermissionName()));
        }
        return authorizationInfo;
    }

    /**
     * 用来进行身份认证，验证用户输入的账号和密码是否正确
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        //获取用户输入的账号
        String account = usernamePasswordToken.getUsername();
        log.info(">>>>用户名为："+account);
        User user = loginService.getUserByAccount(account);
        if (user == null){
            log.error("账号不存在");
            throw new UnknownAccountException("账号 "+account+" 不存在");
        }
        SimpleAuthenticationInfo authorizationInfo = new SimpleAuthenticationInfo(
                user, user.getPassword(), getName());
        return authorizationInfo;
    }
}
