package com.web.poseidon.service;

import com.web.poseidon.domain.User;
import com.web.poseidon.repositories.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    static final Logger logger = LogManager
            .getLogger(UserDetailsServiceImpl.class);

    /**
     * Authorization process
     * @param userName user name
     * @return user details
     * @throws UsernameNotFoundException user not found
     */
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(userName);
        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());

        logger.info("User connected : " + userName + " role : " + authority);

        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), Collections.singletonList(authority));
    }

}
