package com.poddo.userservice.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poddo.userservice.enums.Role;
import com.poddo.userservice.model.User;
import com.poddo.userservice.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class UserControllerTest {
    @Autowired
    private UserRepository userRepository;

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private WebApplicationContext webApplicationContext;

    private User user;
    private User user2;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        user = new User("user", "user", Role.USER);
        user2 = new User("user2", "user2", Role.ADMIN);
        userRepository.save(user);
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void findAll() throws Exception {
        mockMvc.perform(get("/users")).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].username").value("user"));
    }

    @Test
    void findById() throws Exception {
        mockMvc.perform(get("/users/"+user.getId())).andExpect(status().isOk())
                .andExpect(jsonPath("username").value("user"));
    }

    @Test
    void findByUsername() throws Exception {
        mockMvc.perform(get("/users/username/"+user.getUsername())).andExpect(status().isOk())
                .andExpect(jsonPath("username").value("user"));
    }

    @Test
    void createAdmin() throws Exception {
        mockMvc.perform(post("/users/admin")
                .content(objectMapper.writeValueAsString(user2))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("role").value("ADMIN"));
    }

    @Test
    void createUser() throws Exception {
        mockMvc.perform(post("/users/user")
                .content(objectMapper.writeValueAsString(user2))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("role").value("USER"));
    }

    @Test
    void subscribe() throws Exception {
        mockMvc.perform(post("/users/"+user.getId()+"/subscribe/1")
                .content(objectMapper.writeValueAsString(null))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("role").value("USER"));
    }

    @Test
    void unsubscribe() throws Exception {
        mockMvc.perform(post("/users/"+user.getId()+"/unsubscribe/1")
                .content(objectMapper.writeValueAsString(null))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("role").value("USER"));
    }

    @Test
    void update() throws Exception {
        mockMvc.perform(patch("/users/"+user.getId())
                .content(objectMapper.writeValueAsString(user))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void remove() throws Exception {
        mockMvc.perform(delete("/users/"+user.getId()))
                .andExpect(status().isNoContent());
    }
}