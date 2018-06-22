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

import cn.piesat.exam.domain.Dept;
import cn.piesat.exam.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author Joe Grandja
 */
@Controller
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

    @PostMapping("/reguser")
    public String test1(Model model) {
        model.addAttribute("loginSuccess", true);
        return "regSuccess";
    }

//    @RequestMapping("/index")
//    public String index() {
//        return "index";
//    }

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