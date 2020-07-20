package com.poddo.channelservice.repository;

import com.poddo.channelservice.model.Channel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChannelRepository extends JpaRepository<Channel, Long> {
    @Query("SELECT c FROM Channel c WHERE c.name LIKE CONCAT('%',:name,'%') ORDER BY COUNT(c.subscribers) DESC")
    List<Channel> findByNameLikeOrderBySubscribersCount(@Param("name") String name);
}
