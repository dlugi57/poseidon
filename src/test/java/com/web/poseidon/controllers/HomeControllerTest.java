package com.web.poseidon.controllers;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.security.Principal;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private Principal principal;

    static String user = "test";

    @Test
    void home() throws Exception {
        //WHEN //THEN
        when(principal.getName()).thenReturn(user);
        mockMvc.perform(get("/").principal(principal))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("home"));
    }

    @Test
    void adminHome() throws Exception {
        //WHEN //THEN
        when(principal.getName()).thenReturn(user);
        mockMvc.perform(get("/admin/home").principal(principal))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/bidList/list"));
    }
}