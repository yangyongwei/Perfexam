package cn.piesat.exam.mapper;

import cn.piesat.exam.domain.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DeptMapper {

    @Select("select * from dept where id=#{id}")
    Dept findDeptById(@Param("id") Integer id);

    @Select("select id from dept where dept_name=#{dept_name}")
    Integer findDeptIdByName(@Param("dept_name") String dept_name);

    @Select("select * from dept order by dept_name")
    List<Dept> findAllDept();
}