package cn.piesat.exam;

import cn.piesat.exam.domain.Employee;
import cn.piesat.exam.mapper.EmployeeMapper;
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
    private EmployeeMapper employeeMapper;
    @Test
    public void contextLoads() {
        Employee e = employeeMapper.findEmployeeById(7);
        Assert.assertEquals("admin",e.getName());
    }

}
