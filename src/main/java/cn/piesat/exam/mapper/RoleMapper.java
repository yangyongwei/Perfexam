package cn.piesat.exam.mapper;

import cn.piesat.exam.domain.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoleMapper {
    @Select("select * from role where id=#{id}")
    Role findRoleById(@Param("id") Integer id);

    @Select("select * from role where role_name=#{role_name}")
    Role findRoleByName(@Param("role_name") String role_name);

    @Select("select * from role order by id desc")
    List<Role> findAllRole();

}