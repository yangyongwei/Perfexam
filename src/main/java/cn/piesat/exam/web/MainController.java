package cn.piesat.exam.web;

/*
 * Copyright 2002-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import cn.piesat.exam.common.RegUserInfo;
import cn.piesat.exam.domain.*;
import cn.piesat.exam.service.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @author Joe Grandja
 */
@Controller
@Log4j2
public class MainController {

    @Autowired
    private DeptService deptService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private DeptGroupService deptGroupService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private UserDeptGroupService userDeptGroupService;

    @RequestMapping("/")
    public String root() {
        return "redirect:/login";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/loginWith")
    public String loginWithUserName(@RequestParam(name = "userName") String userName, Model model) {
        model.addAttribute("userName", userName);
        return "login";
    }

//    @PostMapping("/regUser")
//    public String regUser(HttpServletRequest request, Model model) {
//        String regUserName = request.getParameter("regUserName");
//        String role = request.getParameter("roleOptions");
//        String dept = request.getParameter("dept");
//        log.warn("注册的部门是：" + dept);
//
//        log.warn("注册的用户名是：" + regUserName);
//        log.warn("用户角色是：" + role);
//        model.addAttribute("regSuccess", true);
//        return "regSuccess";
//    }

    @PostMapping("/regUser")
    public String regUser(@ModelAttribute RegUserInfo r, Model model) {
        // 取得前端传过来的数据
        String userName = r.getRegUserName().trim();
        String pwd = r.getPwd1();
        int roleId = r.getRoleOptions();
        String roleName = null;
        String realName = r.getRealName().trim();
        String deptName = r.getDeptList().trim();
        String groupName = r.getGroupList().trim();
        Integer deptId = null;
        Integer groupId = null;
        Integer userId = null;

        // 保存部门
        Dept dept = deptService.findDeptByName(deptName);
        if (dept == null) {
            Dept d = new Dept();
            d.setDeptName(deptName);
            deptService.add(d);
            log.info("部门：'" + deptName + "' 保存成功！");
            deptId = d.getId();
        } else {
            deptId = dept.getId();
        }

        // 保存组
        if (groupName != "") {
            Group group = groupService.findGroupByName(groupName);
            if (group == null) {
                Group g = new Group();
                g.setGroupName(groupName);
                groupService.add(g);
                log.info("分组：'" + groupName + "' 保存成功！");
                groupId = g.getId();
            } else {
                groupId = group.getId();
            }
        } else {
            groupId = null;
        }

        // 保存部门和组
        if (groupId != null) {
            DeptGroup deptGroup = new DeptGroup();
            deptGroup.setDeptId(deptId);
            deptGroup.setGroupId(groupId);
            deptGroupService.add(deptGroup);
            log.info("部门：'" + deptName + "' 分组:'" + groupName + "' 保存成功！");
        }


        // 保存用户
        User u = new User();
        u.setUsername(userName);
        BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
        u.setPassword(pe.encode(pwd));
        u.setName(realName);
        Timestamp ts = new Timestamp(new Date().getTime());
        u.setCreateTime(ts);
        userService.add(u);
        userId = u.getId();

        // 保存用户、角色
        roleName = roleService.findById(roleId).getRoleName();
        UserRole userRole = new UserRole();
        userRole.setUserId(userId);
        userRole.setRoleId(roleId);
        userRoleService.add(userRole);
        log.info("用户：'" + userName + "' 角色:'" + roleName + "' 保存成功！");

        // 保存用户、部门、分组
        UserDeptGroup uDG = new UserDeptGroup();
        uDG.setUserId(userId);
        uDG.setDeptId(deptId);
        uDG.setGroupId(groupId);

        userDeptGroupService.add(uDG);
        log.info("用户：" + userName + "部门：'" + deptName + "' 分组:'" + groupName + "' 保存成功！");

        model.addAttribute("regSuccess", true);
        model.addAttribute("userName", userName);
        return "regSuccess";
    }

    @RequestMapping("/register")
    public String register(Model model) {

        List<Dept> deptList = deptService.findAllDept();
        model.addAttribute("deptList", deptList);
        List<Role> roleList = roleService.findAllRole();

        Role adminRole = new Role();
        adminRole.setRoleName("ROLE_管理员");
        roleList.remove(adminRole);

        model.addAttribute("roleList", roleList);
        return "register";
    }


    @RequestMapping("/main")
    public String mainPage() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getAuthorities().toString().equals("[ROLE_管理员]")) {
            return "main1";
        } else if (auth.getAuthorities().toString().equals("[ROLE_总监]")) {
            return "home";
        } else if (auth.getAuthorities().toString().equals("[ROLE_组长]")) {
            return "main";
        } else if (auth.getAuthorities().toString().equals("[ROLE_员工]")) {
            return "main";
        } else {
            return "error";
        }
    }

}