package cn.piesat.exam.service;

import cn.piesat.exam.domain.UserDeptGroup;
import cn.piesat.exam.mapper.UserDeptGroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDeptGroupService {

    @Autowired
    UserDeptGroupMapper userDeptGroupMapper;

    public UserDeptGroup findUserDeptGroupByUserId(Integer userId) {
        return userDeptGroupMapper.findUserDeptGroupByUserId(userId);
    }

    public void add(UserDeptGroup userDeptGroup) {
        userDeptGroupMapper.add(userDeptGroup);
    }

}
