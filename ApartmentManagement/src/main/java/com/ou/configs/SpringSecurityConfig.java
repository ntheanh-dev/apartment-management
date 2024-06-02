package com.ou.configs;

import com.ou.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = {
        "com.ou.controllers",
        "com.ou.repositories",
        "com.ou.services"
})
@EnableTransactionManagement
@RequiredArgsConstructor
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http)
            throws Exception {
        http.formLogin().loginPage("/login")
                .usernameParameter("username").passwordParameter("password");

        http.formLogin().defaultSuccessUrl("/")
                .failureUrl("/login?error");

        http.logout().logoutSuccessUrl("/login");

        http.exceptionHandling()
                .accessDeniedPage("/login?accessDenied");
//        http.authorizeRequests().antMatchers("/").permitAll()
//                .antMatchers("/**/add")
//                .access("hasRole('ROLE_ADMIN')");
//        .antMatchers("/**/pay")
//                .access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
        http.csrf().disable();
    }
}
