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

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Joe Grandja
 */
@Controller
public class MainController {

    @RequestMapping("/")
    public String root() {
        return "redirect:/login";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

//    @RequestMapping("/user/index")
//    public String userIndex() {
//        return "user/index";
//    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/main")
    public String mainPage() {
        return "main";
    }

    @RequestMapping("/login?error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "index";
    }

}