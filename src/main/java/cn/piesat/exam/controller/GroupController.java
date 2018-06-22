package cn.piesat.exam.controller;

import cn.piesat.exam.domain.Group;
import cn.piesat.exam.service.DeptGroupService;
import cn.piesat.exam.service.DeptService;
import cn.piesat.exam.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/group")
public class GroupController {

    @Autowired
    private DeptService deptService;
    @Autowired
    private GroupService groupService;
    @Autowired
    private DeptGroupService deptGroupService;

//    @GetMapping("/deptby")
//    public List<String> GroupListByDeptId(@RequestParam(value="id") Integer id)
//    {
//        List<String> groupNames = new ArrayList();
//        List<Integer>  groupIds = deptGroupService.findGroupIdsByDeptId(id);
//        if(groupIds.isEmpty()){
//            return groupNames;
//        }
//        List<Group> groupList = groupService.findGroupByIds(groupIds);
//        for(Group group : groupList){
//            groupNames.add(group.getGroupName());
//        }
//        return groupNames;
//    }

    @GetMapping("/findGroupBy")
    public List<String> GroupListByDeptName(@RequestParam(value="deptName") String deptName)
    {
        List<String> groupNames = new ArrayList();
        Integer id = deptService.findDeptIdByName(deptName);
        List<Integer>  groupIds = deptGroupService.findGroupIdsByDeptId(id);
        if(groupIds.isEmpty()){
            return groupNames;
        }
        List<Group> groupList = groupService.findGroupByIds(groupIds);
        for(Group group : groupList){
            groupNames.add(group.getGroupName());
        }
        return groupNames;
    }
}