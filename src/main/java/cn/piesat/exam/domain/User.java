package cn.piesat.exam.domain;

import lombok.Data;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.thymeleaf.util.StringUtils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@ToString
public class User implements UserDetails {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private String gender;
    private Timestamp createTime;
    private Timestamp lastLoginTime;
    private String status;

    // 用户角色
    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getAuthoritiesString() {
        List<String> authorities = new ArrayList<>();
        for (GrantedAuthority ga : this.getAuthorities()) {
            authorities.add(ga.getAuthority().substring(5));
        }
        return StringUtils.join(authorities, ",");
    }
}
