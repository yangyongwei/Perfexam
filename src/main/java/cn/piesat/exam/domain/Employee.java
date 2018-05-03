package cn.piesat.exam.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Employee {
    private Integer id;
    private String name;
    private String password;
    private String phone;
}
