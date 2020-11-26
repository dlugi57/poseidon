package com.web.poseidon.configuration;

import com.web.poseidon.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // initialize objects

    @Autowired
    private UserDetailsServiceImpl userService;


    /**
     * Field injection of user service
     *
     * @param userService user service
     */
    // TODO: 27/11/2020 this one is not working
/*    @Autowired
    public void setUserService(UserDetailsServiceImpl userService) {
        this.userService = userService;
    }*/

    /**
     * Set user authorization levels and global log in and out parameters
     *
     * @param http request
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        FormLoginConfigurer<HttpSecurity> httpSecurityFormLoginConfigurer;
        // httpSecurityFormLoginConfigurer =
        http.authorizeRequests()
                .antMatchers("/user/**").hasAnyAuthority("ADMIN")
                .antMatchers("/*").hasAnyAuthority("ADMIN", "USER")
                //.antMatchers("/admin").hasRole("ADMIN")
                //.antMatchers("/user").hasRole("USER")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .defaultSuccessUrl("/bidList/list")
                .and().logout()    //logout configuration
                .logoutUrl("/app-logout")
                .logoutSuccessUrl("/")
                .and().exceptionHandling()
                .accessDeniedPage("/app/error")
        ;

    }

    /**
     * Encrypting password
     *
     * @param auth authentication credentials
     * @throws Exception
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }

/*    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       // BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        auth.inMemoryAuthentication()
                .withUser("springuser").password(passwordEncoder().encode("spring123")).roles("USER")
                .and()
                .withUser("springadmin").password(passwordEncoder().encode("admin123")).roles("ADMIN", "USER");
    }*/

/*    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }*/
/*
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }*/
}


