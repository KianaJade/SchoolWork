package cn.kiana.unic.mapper;

import cn.kiana.unic.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User getUserByPhone(String phone);
    void insertUser(User user);
    void updateUser(User user);
}
