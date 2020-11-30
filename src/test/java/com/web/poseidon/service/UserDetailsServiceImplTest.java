package com.web.poseidon.service;

import com.web.poseidon.domain.User;
import com.web.poseidon.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class UserDetailsServiceImplTest {
    @TestConfiguration
    static class UserDetailsServiceTestContextConfiguration {
        @Bean
        public UserDetailsService userDetailsService() {
            return new UserDetailsServiceImpl();
        }
    }

    @Resource
    UserDetailsServiceImpl userDetailsService;
    @MockBean

    private UserRepository userRepository;

    @Test
    void loadUserByUsername() {
        User user = new User();
        // GIVEN
        user.setId(1);
        user.setUsername("test");
        user.setPassword("test");
        user.setRole("USER");
        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());

        Collections.singletonList(authority);
        given(userRepository.findByUsername(anyString())).willReturn(user);
        // WHEN
        UserDetails userTest = userDetailsService.loadUserByUsername("test");
        // THEN
        verify(userRepository, times(1)).findByUsername(anyString());
        //assertThat(userTest).isEqualTo(user);


    }
}