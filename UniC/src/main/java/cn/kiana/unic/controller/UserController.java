package cn.kiana.unic.controller;

import cn.kiana.unic.pojo.Result;
import cn.kiana.unic.pojo.User;
import cn.kiana.unic.service.UserService;
import cn.kiana.unic.utils.SaveImgUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;

@Slf4j  //日志
@RestController
@CrossOrigin(maxAge = 3600)
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/updateUserAvatar")
    public Result updateUserInfo(User user) throws IOException {
        SaveImgUtil saveImgUtil = new SaveImgUtil();
        saveImgUtil.saveImg(user.getFile());
        user = userService.getUserByPhone(user.getPhone());
        saveImgUtil.deleteImg(user.getImg());
        user.setImg(saveImgUtil.fileName);
        user.setPassword("");
        userService.updateUser(user);
        return Result.success(user);
    }

    @GetMapping("/getUserInfo")
    public Result getUserInfo(@RequestParam String phone) throws IOException {
        User user = userService.getUserByPhone(phone);
        user.setPassword("");
        return Result.success(user);
    }

    @PostMapping("/updateUserBaseInfo")
    public Result updateUserBaseInfo(@RequestBody User user) throws IOException {
        user.setImg(userService.getUserByPhone(user.getPhone()).getImg());
        userService.updateUser(user);
        return Result.success();
    }
}
