package cn.piesat.exam.service;

import cn.piesat.exam.domain.User;
import cn.piesat.exam.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public User findById(Integer id){
        return userMapper.findUserById(id);
    }

    public User findByUsername(String userName){
        return userMapper.findUserByUsername(userName);
    }
}
