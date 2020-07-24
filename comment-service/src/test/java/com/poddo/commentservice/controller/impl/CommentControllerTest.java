package com.poddo.commentservice.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poddo.commentservice.model.Comment;
import com.poddo.commentservice.repository.CommentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class CommentControllerTest {
    @Autowired
    private CommentRepository commentRepository;

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private WebApplicationContext webApplicationContext;

    private Comment comment;
    private Comment comment2;

    @BeforeEach
    void setUp() {
        commentRepository.deleteAll();
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        comment = new Comment((long) 1, "comment", null);
        comment2 = new Comment((long) 2, "comment2", null);
        comment = commentRepository.save(comment);
    }

    @AfterEach
    void tearDown() {
        commentRepository.deleteAll();
    }

    @Test
    void findAll() throws Exception {
        mockMvc.perform(get("/comments")).andExpect(status().isOk());
    }

    @Test
    void findById() throws Exception {
        mockMvc.perform(get("/comments/"+comment.getId())).andExpect(status().isOk());
    }

    @Test
    void create() throws Exception {
        mockMvc.perform(post("/comments")
                .content(objectMapper.writeValueAsString(comment2))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void star() throws Exception {
        mockMvc.perform(post("/comments/"+comment.getId()+"/star")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void update() throws Exception {
        mockMvc.perform(patch("/comments/"+comment.getId())
                .content(objectMapper.writeValueAsString(comment))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void remove() throws Exception {
        mockMvc.perform(delete("/comments/"+comment.getId()))
                .andExpect(status().isNoContent());
    }
}