package cn.piesat.exam.mapper;

import cn.piesat.exam.domain.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {

    @Select("select * from dept where id=#{id}")
    Dept findDeptById(@Param("id") Integer id);

    @Select("select id from dept where dept_name=#{dept_name}")
    Integer findDeptIdByName(@Param("dept_name") String dept_name);

    @Select("select * from dept where dept_name=#{dept_name}")
    Dept findDeptByName(@Param("dept_name") String dept_name);

    @Select("select * from dept order by CONVERT(dept_name USING gbk)")
    List<Dept> findAllDept();

    @Insert("insert into dept(dept_name) values (#{dept.deptName})")
    @Options(useGeneratedKeys = true, keyProperty = "dept.id")
    void add(@Param("dept") Dept dept);
}