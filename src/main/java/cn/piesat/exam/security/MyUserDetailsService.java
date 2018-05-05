package cn.piesat.exam.security;

import cn.piesat.exam.domain.Role;
import cn.piesat.exam.domain.User;
import cn.piesat.exam.domain.UserRole;
import cn.piesat.exam.service.RoleService;
import cn.piesat.exam.service.UserRoleService;
import cn.piesat.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRoleService userRoleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Collection<GrantedAuthority> authorities = new ArrayList<>();
        User user = userService.findByUsername(username);

        // 判断用户是否存在
        if(user == null){
            throw new UsernameNotFoundException("用户不存在");
        }

        // 添加权限
        UserRole userRole = userRoleService.findByUserId(user.getId());
        Role role = roleService.findById(userRole.getRoleId());
        authorities.add(new SimpleGrantedAuthority(role.getName()));
        user.setAuthorities(authorities);
        System.out.println(user.toString());
        return user;
    }
}