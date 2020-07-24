package com.poddo.commentservice.repository;

import com.poddo.commentservice.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository of Comment Service
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    /**
     * Find Comments by Comment to respond
     * @param comment Comment to respond
     * @return List of Comments
     */
    List<Comment> findByResponseTo(Comment comment);
}
