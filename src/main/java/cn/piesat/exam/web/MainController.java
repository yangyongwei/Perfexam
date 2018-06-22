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
import cn.piesat.exam.domain.Dept;
import cn.piesat.exam.service.DeptService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Joe Grandja
 */
@Controller
@Log4j2
public class MainController {

    @Autowired
    private DeptService deptService;

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
//    public String reguser(HttpServletRequest request, Model model) {
//        String regUserName = request.getParameter("regUserName");
//        String role = request.getParameter("roleOptions");
//
//        log.warn("注册的用户名是：" + regUserName);
//        log.warn("用户角色是：" + role);
//        model.addAttribute("regSuccess", true);
//        return "regSuccess";
//    }

    @PostMapping("/regUser")
    public String reguser(@ModelAttribute RegUserInfo r, Model model) {
        String userName = r.getRegUserName();
        String role = r.getRoleOptions();

        log.warn("注册的用户名是：" + userName);
        log.warn("用户角色是：" + role);
        model.addAttribute("regSuccess", true);
        model.addAttribute("userName", userName);
        return "regSuccess";
    }

    @RequestMapping("/register")
    public String register(Model model) {

        List<Dept> deptList = deptService.findAllDept();
        model.addAttribute("deptList", deptList);
        return "register";
    }


    @RequestMapping("/main")
    public String mainPage() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getAuthorities().toString().equals("[ROLE_管理员]")) {
            return "index";
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

//    @RequestMapping("/login?error")
//    public String loginError(Model model) {
//        model.addAttribute("loginError", true);
//        System.out.println("用户名或密码！！");
//        return "login";
//    }
}