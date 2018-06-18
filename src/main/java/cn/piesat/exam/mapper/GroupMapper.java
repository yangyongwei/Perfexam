package cn.piesat.exam.mapper;

import cn.piesat.exam.common.SimpleSelectInExtendedLanguageDriver;
import cn.piesat.exam.domain.Group;
import org.apache.ibatis.annotations.Lang;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GroupMapper {

    @Select("select * from `group` where id=#{id}")
    Group findGroupById(@Param("id") Integer id);

    @Lang(SimpleSelectInExtendedLanguageDriver.class)
    @Select("select * from `group` where id in (#{ids})")
    List<Group> findGroupByIds(@Param("ids") List<Integer> ids);

}