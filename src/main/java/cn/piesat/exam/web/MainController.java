package cn.piesat.exam.web;

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
import java.util.List;

/**
 * @author Joe Grandja
 */
@Controller
@Log4j2
public class MainController {

    private static final String ADMIN = "管理员";
    private static final String MANAGER = "总监";
    private static final String TEAMLEADER = "组长";
    private static final String STAFF = "员工";
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

    @PostMapping("/regUser")
    public String regUser(@ModelAttribute RegUserInfo r, Model model) {
        // 取得前端传过来的数据
        String userName = r.getRegUserName().trim();
        String pwd = r.getPwd1();
        int roleId = r.getRoleOptions();
        String realName = r.getRealName().trim();
        String deptName = r.getDeptList().trim();
        String groupName = r.getGroupList().trim();
        String roleName = null;
        Integer deptId = null;
        Integer groupId = null;
        Integer userId = null;
        Boolean regStatus = true;
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
        if (!"".equals(groupName)) {
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
        }
        // 保存部门和组
        if (groupId != null) {
            DeptGroup dg = deptGroupService.findDeptGroupByDeptIdGroupId(deptId, groupId);
            if (dg == null) {
                DeptGroup deptGroup = new DeptGroup();
                deptGroup.setDeptId(deptId);
                deptGroup.setGroupId(groupId);
                deptGroupService.add(deptGroup);
                log.info("部门：'" + deptName + "' 分组:'" + groupName + "' 保存成功！");
            }
        }
        // 保存用户
        User u = userService.findByUsername(userName);
        if (u == null) {
            u = new User();
            u.setUsername(userName);
            BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
            u.setPassword(pe.encode(pwd));
            u.setName(realName);
            u.setCreateTime(new Timestamp(System.currentTimeMillis()));
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
            log.info("用户：'" + userName + "' 部门：'" + deptName + "' 分组:'" + groupName + "' 保存成功！");
            regStatus = true;
        } else {
            regStatus = false;
        }
        model.addAttribute("regStatus", regStatus);
        model.addAttribute("userName", userName);
        return "regResult";
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
    public String mainPage(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User u = (User) auth.getPrincipal();
        u.setLastLoginTime(new Timestamp(System.currentTimeMillis()));
//        更新用户登录时间
        userService.updateLoginTime(u);
        Integer userId = u.getId();
        String userRealName = u.getName();
        String roleName = u.getAuthoritiesString();

        UserDeptGroup udg = userDeptGroupService.findUserDeptGroupByUserId(userId);
        String deptName = null;
        String groupName = null;
        if (udg != null) {
            Integer deptId = udg.getDeptId();
            Integer groupId = udg.getGroupId();
            if (deptId != null) {
                deptName = deptService.findDeptById(deptId).getDeptName();
            }
            if (groupId != null) {
                groupName = groupService.findGroupById(groupId).getGroupName();
            }
        }

        model.addAttribute("userRealName", userRealName);
        model.addAttribute("roleName", roleName);
        model.addAttribute("deptName", deptName);
        model.addAttribute("groupName", groupName);

        if (ADMIN.equals(roleName)) {
            return "main1";
        } else if (MANAGER.equals(roleName)) {
            return "home";
        } else if (TEAMLEADER.equals(roleName)) {
            return "main1";
        } else if (STAFF.equals(roleName)) {
            return "main";
        } else {
            return "error";
        }
    }

}