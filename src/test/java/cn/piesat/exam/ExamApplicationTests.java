package cn.piesat.exam;

import cn.piesat.exam.domain.User;
import cn.piesat.exam.mapper.UserMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExamApplicationTests {

    @Autowired
    private UserMapper userMapper;
    @Test
    public void contextLoads() {
        User e = userMapper.findUserById(7);
        Assert.assertEquals("系统管理员",e.getName());
    }

}
