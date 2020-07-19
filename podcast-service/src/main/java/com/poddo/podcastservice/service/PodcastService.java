package com.poddo.podcastservice.service;

import com.poddo.podcastservice.dto.PodcastDto;
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
    @Autowired
    private CloudinaryService cloudinaryService;

    public List<Podcast> findAll() {
        return podcastRepository.findAll();
    }

    public List<Podcast> findAllOrderByStarsDesc(String title) {
        return title==null ?
                podcastRepository.findAllOrderByStarsDesc() :
                podcastRepository.findByTitleLikeOrderByStarsDesc(title);
    }

    public Podcast findById(Long id) {
        return podcastRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("Podcast with id "+id+" not found."));
    }

    public Podcast create(PodcastDto podcastDto) {
        String audio = cloudinaryService.uploadFile(podcastDto.getFile());
        Podcast podcast = new Podcast(podcastDto.getTags(), podcastDto.getTitle(), podcastDto.getDescription(), podcastDto.getStatus(), audio, podcastDto.allowsComments());

        return podcastRepository.save(podcast);
    }

    public Podcast star(Long id) {
        Podcast podcast = findById(id);
        podcast.addStar();

        return podcastRepository.save(podcast);
    }

    public Podcast comment(Long id, Long commentId) {
        Podcast podcast = findById(id);
        podcast.addComment(commentId);

        return podcastRepository.save(podcast);
    }

    public Podcast uncomment(Long id, Long commentId) {
        Podcast podcast = findById(id);
        podcast.removeComment(commentId);

        return podcastRepository.save(podcast);
    }

    public Podcast update(Long id, Podcast podcastUpdate) {
        Podcast podcast = findById(id);

        if (podcastUpdate.getTitle()!=null) podcast.setTitle(podcastUpdate.getTitle());
        if (podcastUpdate.getTags()!=null)  podcast.setTags(podcastUpdate.getTags());
        if (podcastUpdate.getDescription()!=null) podcast.setDescription(podcastUpdate.getDescription());
        if (podcastUpdate.getStatus()!=null) podcast.setStatus(podcastUpdate.getStatus());
        if (podcastUpdate.allowsComments()!=null) podcast.setStatus(podcastUpdate.getStatus());

        return podcastRepository.save(podcast);
    }

    public void remove(Long id) {
        podcastRepository.deleteById(id);
    }
}
