package cn.piesat.exam.mapper;

import cn.piesat.exam.domain.UserRole;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserRoleMapper {
    @Select("select * from user_role where user_id=#{userId}")
    UserRole findRoleByUserId(@Param("userId") Integer userId);

    @Insert("insert into user_role(user_id,role_id) values (#{userRole.userId},#{userRole.roleId})")
    @Options(useGeneratedKeys = true, keyProperty = "userRole.id")
    void add(@Param("userRole") UserRole userRole);

}