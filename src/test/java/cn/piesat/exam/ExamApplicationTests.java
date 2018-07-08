package cn.piesat.exam;

import cn.piesat.exam.domain.PerformRules;
import cn.piesat.exam.domain.Role;
import cn.piesat.exam.domain.User;
import cn.piesat.exam.mapper.PerformRulesMapper;
import cn.piesat.exam.mapper.RoleMapper;
import cn.piesat.exam.mapper.UserMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExamApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PerformRulesMapper performRulesMapper;

    @Test
    public void contextLoads() {
        User e = userMapper.findUserById(7);
        Assert.assertEquals("Admin", e.getName());

        User u = new User();
        u.setUsername("abcdefghi");
        BCryptPasswordEncoder pwd = new BCryptPasswordEncoder();
//        System.out.println();
        u.setPassword(pwd.encode("password"));
        u.setName("杨永威");
        Timestamp ts = new Timestamp(new Date().getTime());
        u.setCreateTime(ts);
        userMapper.add(u);
        System.out.println("ID:" + u.getId());

        List<Role> roles = roleMapper.findAllRole();
        Role adminRole = new Role();
        adminRole.setRoleName("ROLE_管理员");
        int i = roles.indexOf(adminRole);
        System.out.println("索引：" + i);
    }

    @Test
    public void rulesMapper(){
        PerformRules p = new PerformRules();
        p.setType("ABC");
        p.setKpi("xyz");
        p.setRules("HHHHHH");
        p.setWeight(10);
        performRulesMapper.insert(p);
    }

}
