package cn.kiana.unic.controller;

import cn.kiana.unic.jwt.PassToken;
import cn.kiana.unic.pojo.Result;
import cn.kiana.unic.pojo.User;
import cn.kiana.unic.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j  //日志
@RestController
@CrossOrigin(maxAge = 3600)
public class SignInController {
    @Resource
    private UserServiceImpl userServiceImpl;

    @PostMapping("/signIn")
    @PassToken
    public Result register(@RequestBody User user) {
        return userServiceImpl.signIn(user);
    }

    @PostMapping("/signUp")
    @PassToken
    public Result signUp(@RequestBody User user) {
        return userServiceImpl.signUp(user);
    }
}
