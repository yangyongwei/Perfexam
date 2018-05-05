package cn.piesat.exam.mapper;

import cn.piesat.exam.domain.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface RoleMapper {
    @Select("select * from role where id=#{id}")
    Role findRoleById(@Param("id") Integer id);
}