package cn.piesat.exam.service;

import cn.piesat.exam.domain.Role;
import cn.piesat.exam.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    RoleMapper roleMapper;

    public Role findById(Integer id){
        return roleMapper.findRoleById(id);
    }
}
