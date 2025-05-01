package cn.kiana.unic.service;

import cn.kiana.unic.pojo.Result;
import cn.kiana.unic.pojo.User;

public interface UserService {
    public void save(User user);
    public Result signIn(User user);
    public Result signUp(User user);
    public User getUserByPhone(String phone);
    public void updateUser(User user);
}
