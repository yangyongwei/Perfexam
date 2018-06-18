package cn.piesat.exam.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DeptGroupMapper {

    @Select("select * from dept_group where dept_id=#{dept_id}")
    List<Integer> findGroupIdsByDeptId(@Param("dept_id") Integer dept_id);
}