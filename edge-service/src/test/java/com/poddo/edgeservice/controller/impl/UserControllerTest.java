package com.poddo.edgeservice.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poddo.edgeservice.clients.ChannelClient;
import com.poddo.edgeservice.clients.UserClient;
import com.poddo.edgeservice.dto.ChannelUserDto;
import com.poddo.edgeservice.enums.Role;
import com.poddo.edgeservice.model.Channel;
import com.poddo.edgeservice.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class UserControllerTest {
    @MockBean
    private UserClient userClient;
    @MockBean
    private ChannelClient channelClient;

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    private User user;
    private List<User> users;
    private Channel channel;

    @BeforeEach
    void setUp() {
        mockMvc =  MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        user = new User("user", "user", Role.USER);
        user.setId((long) 1);
        users = new ArrayList<>();
        channel = new Channel((long) 1, "channel");

        when(channelClient.findById((long) 1)).thenReturn(channel);
    }

    @Test
    void findAll() throws Exception {
        when(userClient.findAll("")).thenReturn(users);
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk());
    }

    @Test
    void findByUsername() throws Exception {
        when(userClient.findByUsername("name")).thenReturn(user);
        mockMvc.perform(get("/users/name"))
                .andExpect(status().isOk());
    }
}