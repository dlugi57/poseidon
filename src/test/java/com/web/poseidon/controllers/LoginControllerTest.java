package com.web.poseidon.controllers;

import com.web.poseidon.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @Test
    void login() throws Exception {
        //WHEN //THEN
        mockMvc.perform(get("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
        // .andExpect(view().name("login"))
        ;
    }

    //@Test
    //@AutoConfigureMockMvc(addFilters = false)
    // TODO: 29/11/2020 how to do this?
 /*   void getAllUserArticles() throws Exception {

        List<User> usersList = new ArrayList<>();

        //GIVEN
        User user = new User();
        user.setId(1);
        user.setUsername("testUserName");
        user.setPassword("testPassword");
        user.setFullname("testFullname");
        user.setRole("testRole");

        usersList.add(user);
        //WHEN
        when(userRepository.findAll()).thenReturn(usersList);

        //THEN
        mockMvc.perform(get("/app/secure/article-details"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("user/list"));
    }
*/
/*    @Test
    @AutoConfigureMockMvc(addFilters = false)
    void error() throws Exception {
        //WHEN //THEN
        mockMvc.perform(get("/error")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is5xxServerError())
        // .andExpect(view().name("403"))
        ;
    }*/
}