package cn.piesat.exam.configure;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
public class AuthConfig extends WebSecurityConfigurerAdapter {

    /**
     * 重写该方法，添加自定义用户
     * */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        System.out.println("Good Luck");
        auth.inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance())
                .withUser("admin").password("admin").roles("ADMIN")
                .and()
                .withUser("terry").password("terry").roles("USER")
                .and()
                .withUser("larry").password("larry").roles("USER");
    }
}