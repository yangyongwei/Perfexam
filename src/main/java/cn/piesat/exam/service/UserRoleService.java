package cn.piesat.exam.service;

import cn.piesat.exam.domain.UserRole;
import cn.piesat.exam.mapper.UserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleService {

    @Autowired
    UserRoleMapper userRoleMapper;

    public UserRole findByUserId(Integer userId){
        return userRoleMapper.findRoleByUserId(userId);
    }
}
