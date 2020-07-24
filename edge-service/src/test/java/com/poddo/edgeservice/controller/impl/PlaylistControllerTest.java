package com.poddo.edgeservice.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poddo.edgeservice.clients.PodcastClient;
import com.poddo.edgeservice.enums.ChannelStatus;
import com.poddo.edgeservice.enums.Role;
import com.poddo.edgeservice.model.Playlist;
import com.poddo.edgeservice.model.User;
import com.poddo.edgeservice.service.UserService;
import com.poddo.edgeservice.viewModel.ChannelView;
import com.poddo.edgeservice.viewModel.UserView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class PlaylistControllerTest {
    @MockBean
    private PodcastClient podcastClient;
    @MockBean
    private UserService userService;

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    private Playlist playlist;
    private Playlist playlist2;
    private List<Playlist> playlists;

    @BeforeEach
    void setUp() {
        mockMvc =  MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        playlist = new Playlist("", "");
        playlist2 = new Playlist("", "");
        playlists = new ArrayList<>();
    }

    @Test
    void findAll() throws Exception {
        when(podcastClient.findAllPlaylists()).thenReturn(playlists);
        mockMvc.perform(get("/playlists"))
                .andExpect(status().isOk());
    }

    @Test
    void findById() throws Exception {
        when(podcastClient.findPlaylistById("1")).thenReturn(playlist);
        mockMvc.perform(get("/playlists/1"))
                .andExpect(status().isOk());
    }

    @Test
    void create() throws Exception {
        when(podcastClient.createPlaylist(playlist2)).thenReturn(playlist2);
        mockMvc.perform(post("/playlists/1")
                .header("Authorization","user")
                .content(objectMapper.writeValueAsString(playlist2))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }
}