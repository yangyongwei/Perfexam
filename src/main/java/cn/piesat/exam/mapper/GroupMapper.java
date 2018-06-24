package cn.piesat.exam.mapper;

import cn.piesat.exam.common.SimpleSelectInExtendedLanguageDriver;
import cn.piesat.exam.domain.Group;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface GroupMapper {

    @Select("select * from `group` where id=#{id}")
    Group findGroupById(@Param("id") Integer id);

    @Select("select * from `group` where group_name=#{group_name}")
    Group findGroupByName(@Param("group_name") String group_name);

    @Lang(SimpleSelectInExtendedLanguageDriver.class)
    @Select("select * from `group` where id in (#{ids}) order by CONVERT(group_name USING gbk)")
    List<Group> findGroupByIds(@Param("ids") List<Integer> ids);

    @Insert("insert into `group`(group_name) values (#{group.groupName})")
    @Options(useGeneratedKeys = true, keyProperty = "group.id")
    void add(@Param("group") Group group);

}