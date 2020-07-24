package com.poddo.podcastservice.service;

import com.poddo.podcastservice.exceptions.IdNotFoundException;
import com.poddo.podcastservice.model.Podcast;
import com.poddo.podcastservice.repository.PodcastRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PodcastService {
    @Autowired
    private PodcastRepository podcastRepository;

    public List<Podcast> findAll() {
        return podcastRepository.findAll();
    }

    public List<Podcast> findAllOrderByStarsDesc(String title, String tag) {
        return title==null ?
                tag==null ?
                        podcastRepository.findAllOrderByStarsDesc() :
                        podcastRepository.findByTagContainingOrderByStarsDesc(tag) :
                podcastRepository.findByTitleContainingOrderByStarsDesc(title);
    }

    public Podcast findById(String id) {
        return podcastRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("Podcast with id "+id+" not found."));
    }

    public Podcast create(Podcast podcastDto) {
        Podcast podcast = new Podcast(
                podcastDto.getTags(),
                podcastDto.getTitle(),
                podcastDto.getDescription(),
                podcastDto.getStatus(),
                podcastDto.isAllowComments(),
                podcastDto.getChannelId()
        );

        return podcastRepository.save(podcast);
    }

    public Podcast updatePodcastAudio(String id, String file) {
        Podcast podcast = findById(id);
        podcast.setAudio(file);

        return podcastRepository.save(podcast);
    }

    public Podcast star(String id) {
        Podcast podcast = findById(id);
        podcast.addStar();

        return podcastRepository.save(podcast);
    }

    public Podcast comment(String id, Long commentId) {
        Podcast podcast = findById(id);
        podcast.addComment(commentId);

        return podcastRepository.save(podcast);
    }

    public Podcast uncomment(String id, Long commentId) {
        Podcast podcast = findById(id);
        podcast.removeComment(commentId);

        return podcastRepository.save(podcast);
    }

    public Podcast update(String id, Podcast podcastDto) {
        Podcast podcast = findById(id);

        if (podcastDto.getTitle()!=null) podcast.setTitle(podcastDto.getTitle());
        if (podcastDto.getTags()!=null)  podcast.setTags(podcastDto.getTags());
        if (podcastDto.getDescription()!=null) podcast.setDescription(podcastDto.getDescription());
        if (podcastDto.getStatus()!=null) podcast.setStatus(podcastDto.getStatus());
        podcast.setAllowComments(podcastDto.isAllowComments());

        return podcastRepository.save(podcast);
    }

    public void remove(String id) {
        podcastRepository.deleteById(id);
    }
}
