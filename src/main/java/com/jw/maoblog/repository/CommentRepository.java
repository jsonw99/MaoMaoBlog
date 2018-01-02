package com.jw.maoblog.repository;

import com.jw.maoblog.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Comment Repository repository
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {
 
}