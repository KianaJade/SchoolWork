package cn.kiana.unic.service.impl;

import cn.kiana.unic.jwt.JwtTokenUtils;
import cn.kiana.unic.mapper.UserMapper;
import cn.kiana.unic.pojo.Result;
import cn.kiana.unic.pojo.User;
import cn.kiana.unic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void save(User user) {

    }

    @Override
    public Result signIn(User user) {
        if(!user.checkPhone()){return Result.error("手机号码格式错误");}
        if(!user.checkPassword())return Result.error("密码格式错误");

        User u1 = userMapper.getUserByPhone(user.getPhone());
        if (u1 != null) {
            return Result.error("该手机号已存在账号");
        }
        userMapper.insertUser(user);

        return signUp(user);
    }

    @Override
    public Result signUp(User user) {
        if(!user.checkPhone()){return Result.error("手机号码格式错误");}
        if(!user.checkPassword())return Result.error("密码格式错误");

        User u1 = userMapper.getUserByPhone(user.getPhone());
        if (u1 == null) {return Result.error("该账号不存在");}
        if (!u1.getPassword().equals(user.getPassword())) return Result.error("密码错误");

        u1.setToken(JwtTokenUtils.getToken(u1.getPhone(),u1.getPassword()));
        u1.setPassword(null);
        return Result.success(u1);
    }

    @Override
    public User getUserByPhone(String phone) {
        return userMapper.getUserByPhone(phone);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }
}
