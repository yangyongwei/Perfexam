package cn.piesat.exam.service;

import cn.piesat.exam.domain.Group;
import cn.piesat.exam.mapper.GroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    @Autowired
    GroupMapper groupMapper;

    public Group findGroupById(int id){
        return groupMapper.findGroupById(id);
    }

    public List<Group> findGroupByIds(List<Integer> ids){
        return groupMapper.findGroupByIds(ids);
    }
}
