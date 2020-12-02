package com.web.poseidon.integration;

import com.web.poseidon.domain.User;
import com.web.poseidon.repositories.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@WithUserDetails("test")
public class UserIT {

    @Autowired
    private UserRepository bidListRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @BeforeAll
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    static String username = "username";
    static String password = "password123_PIOTR";
    static String fullname = "fullname";
    static String role = "role";

    @Test
    @WithUserDetails("test")
    void validateUser() throws Exception {

        //GIVEN
        List<User> userBeforeAdd = bidListRepository.findAll();

        // WHEN
        mockMvc.perform(post("/user/validate")
                .param("username", username)
                .param("password", password)
                .param("fullname", fullname)
                .param("role", role))
                .andDo(print())
                .andExpect(view().name("redirect:/user/list"))
                .andExpect(status().is3xxRedirection());
        List<User> userAfterAdd = bidListRepository.findAll();

        //THEN
        assertEquals(userAfterAdd.size(), userBeforeAdd.size() + 1);
    }

}
