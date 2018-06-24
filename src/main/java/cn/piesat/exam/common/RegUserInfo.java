package cn.piesat.exam.common;

import lombok.Data;

@Data
public class RegUserInfo {

    private String regUserName;
    private String pwd0;
    private String pwd1;
    private String realName;
    private String deptList;
    private String groupList;
    private int roleOptions;

}
