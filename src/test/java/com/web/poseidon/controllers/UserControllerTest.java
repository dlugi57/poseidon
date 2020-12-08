package com.web.poseidon.controllers;

import com.web.poseidon.domain.User;
import com.web.poseidon.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private Principal principal;

    @MockBean
    private UserRepository userRepository;

    static String userLogged = "test";
    static String username = "username";
    static String password = "password123_PIOTR";
    static String fullname = "fullname";
    static String role = "role";
    static String incorrectRole = null;

    @Test
    void home() throws Exception {
        List<User> userLists = new ArrayList<>();

        //GIVEN
        User user = new User();
        user.setId(1);
        user.setRole(role);
        user.setFullname(fullname);
        user.setUsername(username);
        user.setPassword(password);

        userLists.add(user);
        //WHEN
        when(principal.getName()).thenReturn(userLogged);
        when(userRepository.findAll()).thenReturn(userLists);

        //THEN
        mockMvc.perform(get("/user/list").principal(principal))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("user/list"));
    }

    @Test
    void addUserForm() throws Exception {
        //WHEN //THEN
        mockMvc.perform(get("/user/add"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("user/add"));
    }

    @Test
    void validate() throws Exception {
        List<User> userLists = new ArrayList<>();

        //GIVEN
        User user = new User();
        user.setId(1);
        user.setRole(role);
        user.setFullname(fullname);
        user.setUsername(username);
        user.setPassword(password);

        userLists.add(user);
        // WHEN
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(user));

        //THEN
        mockMvc.perform(post("/user/validate")
                .param("username", username)
                .param("password", password)
                .param("fullname", fullname)
                .param("role", role))
                .andDo(print())
                .andExpect(view().name("redirect:/user/list"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void validate_invalid() throws Exception {
        //GIVEN
        List<User> userLists = new ArrayList<>();

        User user = new User();
        user.setId(1);
        user.setRole(role);
        user.setFullname(fullname);
        user.setUsername(username);
        user.setPassword(password);

        userLists.add(user);
        // WHEN
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(user));


        //WHEN //THEN return the update page
        mockMvc.perform(post("/user/validate")
                .param("username", username)
                .param("password", password)
                .param("fullname", fullname)

                .param("role", incorrectRole))
                .andDo(print())
                .andExpect(view().name("user/add"))
                .andExpect(status().isOk());
    }

    @Test
    void showUpdateForm() throws Exception {
        //GIVEN
        User user = new User();
        user.setId(1);
        user.setRole(role);
        user.setFullname(fullname);
        user.setUsername(username);
        user.setPassword(password);

        //WHEN
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(user));

        //THEN
        mockMvc.perform(get("/user/update/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("user/update"));
    }

    @Test
    void updateUser() throws Exception {
        //GIVEN
        User user = new User();
        user.setId(1);
        user.setRole(role);
        user.setFullname(fullname);
        user.setUsername(username);
        user.setPassword(password);
        //WHEN
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(user));

        //THEN
        mockMvc.perform(post("/user/update/1")
                .param("username", username)
                .param("password", password)
                .param("fullname", fullname)

                .param("role", role))
                .andDo(print())
                .andExpect(view().name("redirect:/user/list"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void updateUser_Invalid() throws Exception {
        //GIVEN
        User user = new User();
        user.setId(1);
        user.setRole(role);
        user.setFullname(fullname);
        user.setUsername(username);
        user.setPassword(password);
        //WHEN
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(user));

        //THEN
        mockMvc.perform(post("/user/update/1")
                .param("username", username)
                .param("password", password)
                .param("fullname", fullname)
                .param("role", incorrectRole))
                .andDo(print())
                .andExpect(view().name("user/update"))
                .andExpect(status().isOk());

    }

    @Test
    void deleteUser() throws Exception {
        //GIVEN
        User user = new User();
        user.setId(1);
        user.setRole(role);
        user.setFullname(fullname);
        user.setUsername(username);
        user.setPassword(password);

        //WHEN
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(user));

        //THEN
        mockMvc.perform(get("/user/delete/1"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/user/list"));

        verify(userRepository, times(1)).delete(any(User.class));
    }
}