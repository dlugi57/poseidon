package com.web.poseidon.configuration;

import com.web.poseidon.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // initialize objects
    @Autowired
    private UserDetailsServiceImpl userService;

    /**
     * Set user authorization levels and global log in and out parameters
     *
     * @param http request
     * @throws Exception exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        // FormLoginConfigurer<HttpSecurity> httpSecurityFormLoginConfigurer;
        // httpSecurityFormLoginConfigurer =
        http.authorizeRequests()
                .antMatchers("/user/**").hasAnyAuthority("ADMIN")
                .antMatchers("/*").hasAnyAuthority("ADMIN", "USER")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .defaultSuccessUrl("/bidList/list")
                .and().logout()    //logout configuration
                .logoutUrl("/app-logout")
                .logoutSuccessUrl("/")
                .and().exceptionHandling()
                .accessDeniedPage("/app/error");

    }

    /**
     * Encrypting password
     *
     * @param auth authentication credentials
     * @throws Exception exception
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }

}


