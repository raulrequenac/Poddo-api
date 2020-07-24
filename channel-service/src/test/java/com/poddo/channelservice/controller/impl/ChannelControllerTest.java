package com.poddo.channelservice.controller.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poddo.channelservice.model.Channel;
import com.poddo.channelservice.repository.ChannelRepository;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class ChannelControllerTest {
    @Autowired
    private ChannelRepository channelRepository;

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private WebApplicationContext webApplicationContext;

    Channel channel;
    Channel channel2;

    @BeforeEach
    void setUp() {
        channelRepository.deleteAll();
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        channel = new Channel((long) 1, "channel");
        channel2 = new Channel((long) 2, "channel2");
        channelRepository.save(channel);
    }

    @AfterEach
    void tearDown() {
        channelRepository.deleteAll();
    }

    @Test
    void findAll() throws Exception {
        mockMvc.perform(get("/channels")).andExpect(status().isOk());
    }

    @Test
    void findById() throws Exception {
        mockMvc.perform(get("/channels/"+channel.getId())).andExpect(status().isOk());
    }

    @Test
    void getPodcasts() throws Exception {
        mockMvc.perform(get("/channels/"+channel.getId()+"/podcasts")).andExpect(status().isOk());
    }

    @Test
    void getPlaylists() throws Exception {
        mockMvc.perform(get("/channels/"+channel.getId()+"/playlists")).andExpect(status().isOk());
    }

    @Test
    void create() throws Exception {
        mockMvc.perform(post("/channels")
                .content(objectMapper.writeValueAsString(channel2))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void block() throws Exception {
        mockMvc.perform(post("/channels/"+channel.getId()+"/block"))
                .andExpect(status().isOk());
    }

    @Test
    void unblock() throws Exception {
        mockMvc.perform(post("/channels/"+channel.getId()+"/unblock"))
                .andExpect(status().isOk());
    }

    @Test
    void addPodcast() throws Exception {
        mockMvc.perform(post("/channels/"+channel.getId()+"/add/podcast/1"))
                .andExpect(status().isOk());
    }

    @Test
    void removePodcast() throws Exception {
        mockMvc.perform(post("/channels/"+channel.getId()+"/remove/podcast/1"))
                .andExpect(status().isOk());
    }

    @Test
    void addPlaylist() throws Exception {
        mockMvc.perform(post("/channels/"+channel.getId()+"/add/playlist/1"))
                .andExpect(status().isOk());
    }

    @Test
    void removePlaylist() throws Exception {
        mockMvc.perform(post("/channels/"+channel.getId()+"/remove/playlist/1"))
                .andExpect(status().isOk());
    }

    @Test
    void addSubscriber() throws Exception {
        mockMvc.perform(post("/channels/"+channel.getId()+"/add/subscriber"))
                .andExpect(status().isOk());
    }

    @Test
    void removeSubscriber() throws Exception {
        mockMvc.perform(post("/channels/"+channel.getId()+"/remove/subscriber"))
                .andExpect(status().isOk());
    }

    @Test
    void remove() throws Exception {
        mockMvc.perform(delete("/channels/"+channel.getId()))
                .andExpect(status().isNoContent());
    }

    @Test
    void updateLogo() throws Exception {
        mockMvc.perform(post("/channels/"+channel.getId()+"/update")
                .content("file")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}