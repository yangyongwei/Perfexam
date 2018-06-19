package cn.piesat.exam.service;

import cn.piesat.exam.domain.Dept;
import cn.piesat.exam.mapper.DeptMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptService {

    @Autowired
    DeptMapper deptMapper;

    public List<Dept> findAllDept(){
        return deptMapper.findAllDept();
    }

    public Integer findDeptIdByName(String dept_Name){
        return deptMapper.findDeptIdByName(dept_Name);
    }

    public Dept findDeptById(int id){
        return deptMapper.findDeptById(id);
    }
}
