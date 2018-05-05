package cn.piesat.exam.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserRole {
    private Integer userId;
    private Integer roleId;
}
