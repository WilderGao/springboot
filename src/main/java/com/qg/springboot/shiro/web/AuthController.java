package com.qg.springboot.shiro.web;

import com.wilder.safe.dto.Result;
import com.wilder.safe.enums.StatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author WilderGao
 * time 2018-12-06 16:48
 * motto : everything is no in vain
 * description
 */
@RestController
@RequestMapping("/error")
@Slf4j
public class AuthController {

    @GetMapping(value = "/unauth")
    @CrossOrigin
    public Result<String> unAuth(){
        log.error("用户没有权限......");
        return new Result<>(StatusEnum.AUTH_ERROR.getState(), "用户没有权限使用此功能");
    }

    @GetMapping(value = "/unlogin")
    @CrossOrigin
    public Result<String> unLogin(){
        log.info("用户没有登录......");
        return new Result<>(StatusEnum.LOGIN_ERROR.getState(), "用户没有登录");
    }
}
