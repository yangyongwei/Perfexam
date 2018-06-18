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

    @Select("select * from dept order by dept_name")
    List<Dept> findAllDept();
}