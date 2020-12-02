package com.web.poseidon.controllers;

import com.web.poseidon.domain.User;
import com.web.poseidon.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @Test
    void getAllUserArticles() throws Exception {

        List<User> usersList = new ArrayList<>();

        //GIVEN
        User user = new User();
        user.setId(1);
        user.setUsername("testUserName");
        user.setPassword("testPassword");
        user.setFullname("testFullname");
        user.setRole("ADMIN");

        usersList.add(user);
        //WHEN
        when(userRepository.findAll()).thenReturn(usersList);

        //THEN
        mockMvc.perform(get("/app/secure/article-details"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("user/list"));
    }

    @Test
    void error() throws Exception {
        //WHEN //THEN
        mockMvc.perform(get("/app/error")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
}