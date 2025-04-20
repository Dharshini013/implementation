package com.batch2.artifact1.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.batch2.artifact1.service.RegService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(RegController.class)
public class RegControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RegService regService;

    @Test
    public void testShowRegisterPage() throws Exception {
        mockMvc.perform(get("/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"));
    }

    @Test
    public void testRegisterUser_Success() throws Exception {
        when(regService.registerUser("testuser", "password")).thenReturn(true);

        mockMvc.perform(post("/register")
                .param("username", "testuser")
                .param("password", "password"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andExpect(model().attribute("message", "Registration successful! Please login."));
    }

    @Test
    public void testRegisterUser_Failure() throws Exception {
        when(regService.registerUser("testuser", "password")).thenReturn(false);

        mockMvc.perform(post("/register")
                .param("username", "testuser")
                .param("password", "password"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"))
                .andExpect(model().attribute("error", "Username already exists!"));
    }
}
