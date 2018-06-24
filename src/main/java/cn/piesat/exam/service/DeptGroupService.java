package cn.piesat.exam.service;

import cn.piesat.exam.domain.DeptGroup;
import cn.piesat.exam.mapper.DeptGroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptGroupService {

    @Autowired
    DeptGroupMapper deptGroupMapper;

    public List<Integer> findGroupIdsByDeptId(Integer dept_id){
        return deptGroupMapper.findGroupIdsByDeptId(dept_id);
    }

    public void add(DeptGroup deptGroup){
        deptGroupMapper.add(deptGroup);
    }
}
