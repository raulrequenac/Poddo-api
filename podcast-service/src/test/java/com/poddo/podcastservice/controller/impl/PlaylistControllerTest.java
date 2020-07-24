package com.poddo.podcastservice.controller.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poddo.podcastservice.enums.Status;
import com.poddo.podcastservice.model.Playlist;
import com.poddo.podcastservice.model.Podcast;
import com.poddo.podcastservice.repository.PlaylistRepository;
import com.poddo.podcastservice.repository.PodcastRepository;
import com.poddo.podcastservice.service.PodcastService;
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
class PlaylistControllerTest {
    @Autowired
    private PlaylistRepository playlistRepository;
    @Autowired
    private PodcastRepository podcastRepository;

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private WebApplicationContext webApplicationContext;

    private Playlist playlist;
    private Playlist playlist2;
    private Podcast podcast;

    @BeforeEach
    void setUp() {
        playlistRepository.deleteAll();
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        playlist = new Playlist("playlist", "playlist");
        playlist2 = new Playlist("playlist", "playlist2");
        playlistRepository.save(playlist);

        podcast = new Podcast(new ArrayList<>(), "podcast", "podcast", Status.PUBLIC, true, (long) 1);
        podcastRepository.save(podcast);
    }

    @AfterEach
    void tearDown() {
        podcastRepository.deleteAll();
        playlistRepository.deleteAll();
    }

    @Test
    void findAll() throws Exception {
        mockMvc.perform(get("/playlists")).andExpect(status().isOk());
    }

    @Test
    void findById() throws Exception {
        mockMvc.perform(get("/playlists/"+playlist.getId())).andExpect(status().isOk());
    }

    @Test
    void create() throws Exception {
        mockMvc.perform(post("/playlists")
                .content(objectMapper.writeValueAsString(playlist2))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void addPodcast() throws Exception {
        mockMvc.perform(post("/playlists/"+playlist.getId()+"/add/"+podcast.getId()))
                .andExpect(status().isOk());
    }

    @Test
    void removePodcast() throws Exception {
        mockMvc.perform(post("/playlists/"+playlist.getId()+"/remove/"+podcast.getId()))
                .andExpect(status().isOk());
    }

    @Test
    void update() throws Exception {
        mockMvc.perform(patch("/playlists/"+playlist.getId())
                .content(objectMapper.writeValueAsString(playlist))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void remove() throws Exception {
        mockMvc.perform(delete("/playlists/"+playlist.getId()))
                .andExpect(status().isNoContent());
    }
}