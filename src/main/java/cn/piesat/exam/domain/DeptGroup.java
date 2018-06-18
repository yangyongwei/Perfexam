package cn.piesat.exam.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class DeptGroup {
    private Integer id;
    private Integer deptId;
    private Integer groupId;
}
