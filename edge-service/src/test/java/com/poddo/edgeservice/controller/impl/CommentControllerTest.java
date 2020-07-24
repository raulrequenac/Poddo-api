package com.poddo.edgeservice.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poddo.edgeservice.clients.CommentClient;
import com.poddo.edgeservice.model.Comment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class CommentControllerTest {
    @MockBean
    private CommentClient commentClient;

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    private Comment comment;
    private List<Comment> comments;

    @BeforeEach
    void setUp() {
        mockMvc =  MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        comment = new Comment((long) 1, "comment");
        comments = new ArrayList<>();
        comments.add(comment);
    }

    @Test
    void findAll() throws Exception {
        Mockito.when(commentClient.findAll()).thenReturn(comments);
        mockMvc.perform(get("/comments"))
                .andExpect(status().isOk());
    }

    @Test
    void findById() throws Exception {
        Mockito.when(commentClient.findById((long) 1)).thenReturn(comment);
        mockMvc.perform(get("/comments/1"))
                .andExpect(status().isOk());
    }

    @Test
    void star() throws Exception {
        Mockito.when(commentClient.star((long) 1)).thenReturn(comment);
        mockMvc.perform(post("/comments/1/star"))
                .andExpect(status().isOk());
    }

    @Test
    void update() throws Exception {
        Mockito.when(commentClient.update((long) 1, comment)).thenReturn(comment);
        mockMvc.perform(patch("/comments/1")
                .header("Authorization","USER")
                .content(objectMapper.writeValueAsString(comment))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}