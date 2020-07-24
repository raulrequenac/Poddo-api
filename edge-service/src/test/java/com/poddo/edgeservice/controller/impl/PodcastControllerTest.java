package com.poddo.edgeservice.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poddo.edgeservice.clients.CommentClient;
import com.poddo.edgeservice.clients.PodcastClient;
import com.poddo.edgeservice.enums.ChannelStatus;
import com.poddo.edgeservice.enums.PodcastStatus;
import com.poddo.edgeservice.model.Comment;
import com.poddo.edgeservice.model.Podcast;
import com.poddo.edgeservice.service.PodcastService;
import com.poddo.edgeservice.viewModel.ChannelView;
import com.poddo.edgeservice.viewModel.PodcastView;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class PodcastControllerTest {
    @MockBean
    private PodcastClient podcastClient;
    @MockBean
    private CommentClient commentClient;

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    private Podcast podcast;
    private List<Podcast> podcasts;
    private Comment comment;

    @BeforeEach
    void setUp() {
        mockMvc =  MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        podcast = new Podcast(new ArrayList<>(), "", "", PodcastStatus.PUBLIC, "", true, (long) 1);
        podcasts = new ArrayList<>();
        comment = new Comment((long) 1, "");
    }

    @Test
    void findAll() throws Exception {
        when(podcastClient.findAllPodcasts()).thenReturn(podcasts);
        mockMvc.perform(get("/podcasts"))
                .andExpect(status().isOk());
    }

    @Test
    void findAllOrderByStarsDesc() throws Exception {
        when(podcastClient.findAllOrderByStarsDesc("", "")).thenReturn(podcasts);
        mockMvc.perform(get("/podcasts/stars"))
                .andExpect(status().isOk());
    }

    @Test
    void findById() throws Exception {
        when(podcastClient.findPodcastById("1")).thenReturn(podcast);
        mockMvc.perform(get("/podcasts/1"))
                .andExpect(status().isOk());
    }

    @Test
    void star() throws Exception {
        when(podcastClient.starPodcast("1")).thenReturn(podcast);
        mockMvc.perform(post("/podcasts/1/star"))
                .andExpect(status().isOk());
    }

    @Test
    void uncomment() throws Exception {
        when(podcastClient.uncommentPodcast("1", (long) 1)).thenReturn(podcast);
        when(commentClient.findById((long) 1)).thenReturn(comment);
        mockMvc.perform(post("/podcasts/1/uncomment/1"))
                .andExpect(status().isOk());
    }
}