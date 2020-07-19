package com.poddo.channelservice.model;

import com.poddo.channelservice.enums.Status;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Channel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Status status;
    private Long userId;
    private List<Long> subscribers;
    private List<Long> podcasts;

}
