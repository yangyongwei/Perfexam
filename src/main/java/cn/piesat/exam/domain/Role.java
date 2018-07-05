package cn.piesat.exam.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Role {
    private Integer id;
    private String roleName;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Role role = (Role) o;
        return roleName != null ? roleName.equals(role.roleName) : role.roleName == null;
    }
}
