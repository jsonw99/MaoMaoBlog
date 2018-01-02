package com.jw.maoblog.repository;

import com.jw.maoblog.domain.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Vote Repository interface
 */
public interface VoteRepository extends JpaRepository<Vote, Long> {
 
}