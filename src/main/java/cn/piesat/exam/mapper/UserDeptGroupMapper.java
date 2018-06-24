package cn.piesat.exam.mapper;

import cn.piesat.exam.domain.UserDeptGroup;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserDeptGroupMapper {

    @Select("select * from dept_group where user_id=#{user_id}")
    UserDeptGroup findUserDeptGroupByUserId(@Param("user_id") Integer user_id);

    @Insert("insert into user_dept_group(user_id,dept_id,group_id) values (#{userDeptGroup.userId},#{userDeptGroup.deptId},#{userDeptGroup.groupId})")
    @Options(useGeneratedKeys = true, keyProperty = "userDeptGroup.id")
    void add(@Param("userDeptGroup") UserDeptGroup userDeptGroup);
}