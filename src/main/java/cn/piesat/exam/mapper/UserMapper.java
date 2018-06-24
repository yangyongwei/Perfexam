package cn.piesat.exam.mapper;

import cn.piesat.exam.domain.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Select("select * from user where id=#{id}")
    User findUserById(@Param("id") Integer id);

    @Select("select * from user where username=#{username}")
    User findUserByUsername(@Param("username") String username);

    @Insert("insert into user(username, password, name, gender, create_time, last_login_time, status) values (#{user.username},#{user.password},#{user.name},#{user.gender},#{user.createTime},#{user.lastLoginTime},#{user.status})")
    @Options(useGeneratedKeys = true, keyProperty = "user.id")
    void add(@Param("user") User user);
}
