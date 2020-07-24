package com.poddo.edgeservice.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poddo.edgeservice.clients.ChannelClient;
import com.poddo.edgeservice.enums.ChannelStatus;
import com.poddo.edgeservice.model.Channel;
import com.poddo.edgeservice.service.ChannelService;
import com.poddo.edgeservice.service.CloudinaryService;
import com.poddo.edgeservice.viewModel.ChannelView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class ChannelControllerTest {
    @MockBean
    private ChannelClient channelClient;

    @MockBean
    private ChannelService channelService;

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    private Channel channel;
    private List<Channel> channels;
    private List<String> strings;

    @BeforeEach
    void setUp() {
        mockMvc =  MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        channel = new Channel((long) 1, "channel");
        channels = new ArrayList<>();
        strings = new ArrayList<>();
    }

    @Test
    void findAll() throws Exception {
        when(channelClient.findAll(null)).thenReturn(channels);
        mockMvc.perform(get("/channels"))
                .andExpect(status().isOk());
    }

    @Test
    void findById() throws Exception {
        when(channelClient.findById((long) 1)).thenReturn(channel);
        mockMvc.perform(get("/channels/1"))
                .andExpect(status().isOk());
    }

    @Test
    void getPodcasts() throws Exception {
        when(channelClient.getPodcasts((long) 1)).thenReturn(strings);
        mockMvc.perform(get("/channels/1/podcasts"))
                .andExpect(status().isOk());
    }

    @Test
    void getPlaylists() throws Exception {
        when(channelClient.getPodcasts((long) 1)).thenReturn(strings);
        mockMvc.perform(get("/channels/1/playlists"))
                .andExpect(status().isOk());
    }

    @Test
    void block() throws Exception {
        when(channelClient.block((long) 1)).thenReturn(channel);
        mockMvc.perform(post("/channels/1/block"))
                .andExpect(status().isOk());
    }

    @Test
    void unblock() throws Exception {
        when(channelClient.block((long) 1)).thenReturn(channel);
        mockMvc.perform(post("/channels/1/unblock"))
                .andExpect(status().isOk());
    }

    @Test
    void updateLogo() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "logo.jpg", "image/jpg", "".getBytes());
        ChannelView channelView = new ChannelView((long) 1, "", "", ChannelStatus.UNLOCKED, (long) 0, new ArrayList<>(), new ArrayList<>());
        when(channelService.convertToView(channel)).thenReturn(channelView);
        when(channelClient.updateLogo((long) 1, "")).thenReturn(channel);
        mockMvc.perform(MockMvcRequestBuilders.multipart("/channels/1/update")
                .file(file)
                .header("Authorization","USER")
                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    void subscribe() throws Exception {
        when(channelClient.addSubscriber((long) 1)).thenReturn(channel);
        mockMvc.perform(post("/channels/1/subscribe"))
                .andExpect(status().isOk());
    }

    @Test
    void unsubscribe() throws Exception {
        when(channelClient.addSubscriber((long) 1)).thenReturn(channel);
        mockMvc.perform(post("/channels/1/unsubscribe"))
                .andExpect(status().isOk());
    }
}