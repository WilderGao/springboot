package com.qg.springboot.shiro.web;

import com.google.gson.Gson;
import com.wilder.safe.config.TopicRabbitConfig;
import com.wilder.safe.dto.Result;
import com.wilder.safe.enums.StatusEnum;
import com.wilder.safe.model.User;
import com.wilder.safe.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author WilderGao
 * time 2018-12-04 19:35
 * motto : everything is no in vain
 * description
 */
@RestController
@RequestMapping(value = "/user")
@CrossOrigin(allowCredentials = "true")
@Slf4j
public class UserController {
    @Resource
    private RabbitTemplate rabbitTemplate;
    private Gson gson = new Gson();

    @Autowired
    private LoginService loginService;

    @PostMapping("/sign")
    public Result<String> sign(@RequestBody User user){
        log.info(">>>>>> 收到注册消息"+user.toString());
        //将消息发送到消息队列
        rabbitTemplate.convertAndSend(TopicRabbitConfig.getSIGN_QUEUE(), gson.toJson(user));
        return new Result<>(StatusEnum.OK.getState(), "提交成功，等待管理员审批");
    }

    @PostMapping(value = "/login")
    public Result<String> login(@RequestBody User user){
        String account = user.getAccount();
        log.debug("用户登录，开始认证，账号为=>{}",account);
        UsernamePasswordToken token = new UsernamePasswordToken(account, user.getPassword());
        //获取当前用户的Subject
        Subject currentUserSubject = SecurityUtils.getSubject();
        try {
            //在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
            //每个Realm都能在必要时对提交的AuthenticationTokens作出反应
            //所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
            log.info("对用户[" + account + "]进行登录验证..验证开始");
            currentUserSubject.login(token);
            log.info("对用户[" + account + "]进行登录验证..验证通过");
        } catch (UnknownAccountException uae) {
            log.info("对用户[" + account + "]进行登录验证..验证未通过,未知账户");
        } catch (IncorrectCredentialsException ice) {
            log.info("对用户[" + account + "]进行登录验证..验证未通过,错误的凭证");
        } catch (LockedAccountException lae) {
            log.info("对用户[" + account + "]进行登录验证..验证未通过,账户已锁定");
        } catch (ExcessiveAttemptsException eae) {
            log.info("对用户[" + account + "]进行登录验证..验证未通过,错误次数过多");
        } catch (AuthenticationException ae) {
            //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
            log.info("对用户[" + account + "]进行登录验证..验证未通过,堆栈轨迹如下");
            ae.printStackTrace();
        }
        Result<String> result;
        if (currentUserSubject.isAuthenticated()){
            log.info("用户认证成功：{}",account);
            result = new Result<>(StatusEnum.OK.getState(), "登录成功");
        }else {
            token.clear();
            log.error("用户认证失败，{}",account);
            result = new Result<>(StatusEnum.LOGIN_ERROR.getState(), "认证失败");
        }
        return result;
    }

    @GetMapping(value = "/information")
    @RequiresRoles(value = {"admin", "guest"},logical = Logical.OR)
    public Result<User> getInformation(){
        log.info(">>>>>> 获取该用户的信息");
        User userInformation = (User) SecurityUtils.getSubject().getPrincipal();
        System.out.println(userInformation);
        if (userInformation != null) {
            //返回给前端时密码设置为空
            userInformation.setPassword("");
            return new Result<>(StatusEnum.OK.getState(), userInformation);
        }else {
            log.error(">>>>> 用户不存在，出现异常");
            return new Result<>(StatusEnum.USER_ERROR.getState(), null);
        }
    }

    @GetMapping(value = "/members")
    @RequiresRoles(value = {"admin"})
    public Result<List<User>> getAllMember(){
        User userInformation = (User) SecurityUtils.getSubject().getPrincipal();
        if (userInformation == null){
            return new Result<>(StatusEnum.USER_ERROR.getState(), null);
        }
        log.info(">>>>> 获得所有用户的信息");
        return loginService.getAllInformation();
    }
}
