package com.poddo.podcastservice.controller.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poddo.podcastservice.enums.Status;
import com.poddo.podcastservice.model.Podcast;
import com.poddo.podcastservice.repository.PodcastRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class PodcastControllerTest {
    @Autowired
    private PodcastRepository podcastRepository;

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private WebApplicationContext webApplicationContext;

    private Podcast podcast;
    private Podcast podcast2;

    @BeforeEach
    void setUp() {
        podcastRepository.deleteAll();
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        podcast = new Podcast(new ArrayList<>(), "podcast", "podcast", Status.PUBLIC, true, (long) 1);
        podcast2 = new Podcast(new ArrayList<>(), "podcast2", "podcast2", Status.PUBLIC, true, (long) 2);
        podcastRepository.save(podcast);
    }

    @AfterEach
    void tearDown() {
        podcastRepository.deleteAll();
    }

    @Test
    void findAll() throws Exception {
        mockMvc.perform(get("/podcasts")).andExpect(status().isOk());
    }

    @Test
    void findAllOrderByStarsDesc() throws Exception {
        mockMvc.perform(get("/podcasts/stars")).andExpect(status().isOk());
    }

    @Test
    void findById() throws Exception {
        mockMvc.perform(get("/podcasts/"+podcast.getId())).andExpect(status().isOk());
    }

    @Test
    void create() throws Exception {
        mockMvc.perform(post("/podcasts")
                .content(objectMapper.writeValueAsString(podcast2))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void updatePodcastAudio() throws Exception {
        mockMvc.perform(post("/podcasts/"+podcast.getId()+"/update")
                .content("file")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void star() throws Exception {
        mockMvc.perform(post("/podcasts/"+podcast.getId()+"/star"))
                .andExpect(status().isOk());
    }

    @Test
    void comment() throws Exception {
        mockMvc.perform(post("/podcasts/"+podcast.getId()+"/comment/1"))
                .andExpect(status().isOk());
    }

    @Test
    void uncomment() throws Exception {
        mockMvc.perform(post("/podcasts/"+podcast.getId()+"/uncomment/1"))
                .andExpect(status().isOk());
    }

    @Test
    void update() throws Exception {
        mockMvc.perform(patch("/podcasts/"+podcast.getId())
                .content(objectMapper.writeValueAsString(podcast))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void remove() throws Exception {
        mockMvc.perform(delete("/podcasts/"+podcast.getId()))
                .andExpect(status().isNoContent());
    }
}