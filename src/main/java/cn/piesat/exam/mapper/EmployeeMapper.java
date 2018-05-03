package cn.piesat.exam.mapper;

import cn.piesat.exam.domain.Employee;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface EmployeeMapper {
    @Select("select * from tbl_user where id=#{id}")
    Employee findEmployeeById(@Param("id") Integer id);
}
