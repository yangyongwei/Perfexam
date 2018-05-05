package cn.piesat.exam.mapper;

import cn.piesat.exam.domain.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserRoleMapper {
    @Select("select * from user_role where user_id=#{userId}")
    UserRole findRoleByUserId(@Param("userId") Integer userId);
}