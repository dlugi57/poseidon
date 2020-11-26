package com.web.poseidon.service;

import com.web.poseidon.domain.User;
import com.web.poseidon.repositories.BidListRepository;
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

import java.util.Arrays;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    //@Autowired
    //private UserRepository userRepository;

    static final Logger logger = LogManager
            .getLogger(UserDetailsServiceImpl.class);

    // initialize objects
    private UserRepository userRepository;

    /**
     * Field injection of user dao
     *
     * @param userRepository user dao
     */
    @Autowired
    public void setUserDao(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Authorization process
     * @param userName user name 
     * @return user details
     * @throws UsernameNotFoundException
     */
    // TODO: 26/11/2020 please tell me something about this
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(userName);
        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());

        logger.info("User connected : " + userName + " role : " + authority);
        UserDetails userDetails = (UserDetails)new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), Arrays.asList(authority));
        return userDetails;
    }

}
