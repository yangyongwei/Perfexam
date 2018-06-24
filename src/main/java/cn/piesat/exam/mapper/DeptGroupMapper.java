package cn.piesat.exam.mapper;

import cn.piesat.exam.domain.DeptGroup;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptGroupMapper {

    @Select("select * from dept_group where dept_id=#{dept_id}")
    List<Integer> findGroupIdsByDeptId(@Param("dept_id") Integer dept_id);

    @Insert("insert into dept_group(dept_id,group_id) values (#{deptGroup.deptId},#{deptGroup.groupId})")
    @Options(useGeneratedKeys = true, keyProperty = "deptGroup.id")
    void add(@Param("deptGroup") DeptGroup deptGroup);

}