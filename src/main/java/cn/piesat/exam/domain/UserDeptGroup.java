package cn.piesat.exam.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserDeptGroup {
    private Integer id;
    private Integer userId;
    private Integer deptId;
    private Integer groupId;
}