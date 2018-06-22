package cn.piesat.exam.controller;

import cn.piesat.exam.domain.User;
import cn.piesat.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/findBy")
    public Boolean findBy(@RequestParam(value = "userName") String userName) {

        User user = userService.findByUsername(userName);
        return user != null;
    }
}