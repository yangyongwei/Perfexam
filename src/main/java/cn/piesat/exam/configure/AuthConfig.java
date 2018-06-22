package cn.piesat.exam.configure;

import cn.piesat.exam.security.MyUserDetailsService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@Log4j2
public class AuthConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailsService userDetailsService;

    /**
     * 重写该方法，设定用户访问权限
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        BCryptPasswordEncoder e = new BCryptPasswordEncoder();
//        System.out.println(e.encode("password"));
        http.authorizeRequests()
                .antMatchers("/css/**", "/js/**","/bootstrap/**","/login","/register").permitAll()
                .antMatchers("/orders/**").hasRole("管理员")    //用户权限
                .antMatchers("/users/**").hasRole("管理员")
                .and()
                .formLogin()
                .loginPage("/login")    //跳转登录页面的控制器，该地址要保证和表单提交的地址一致！
                .defaultSuccessUrl("/main")
                .failureUrl("/login?error")
                .failureHandler((request, response, exception) -> {
                    System.out.println(exception);
                    response.sendRedirect("/login?error");
                })
                .successHandler((request, response, authentication) -> {
                    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                    if (principal != null && principal instanceof UserDetails) {
                        UserDetails user = (UserDetails) principal;
                        log.info("LoginUser:" + user.getUsername());
//                            request.getSession().setAttribute("userDetail", user);
                            response.sendRedirect("/main");
                    }
                })
                .permitAll()
                .and()
                .rememberMe()
                .tokenValiditySeconds(60)
                .key("luckbird#1")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .permitAll();
//                .and()
//                .csrf()
//                .disable();        //暂时禁用CSRF，否则无法提交表单

        //super.configure(http);
    }

    /**
     * 重写该方法，添加自定义用户
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
//        内存用户
//        auth.
//                passwordEncoder(new BCryptPasswordEncoder());
//        auth.inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance())
//                .withUser("admin").password("$2a$10$NLOmsCBSfOUzpucqcm7rj.mnkjDl9TY2kleIfLxMiPjfyehXOWxBS").roles("ADMIN")
//                .and()
//                .withUser("luck").password("$2a$10$NLOmsCBSfOUzpucqcm7rj.mnkjDl9TY2kleIfLxMiPjfyehXOWxBS").roles("USER");
    }
}