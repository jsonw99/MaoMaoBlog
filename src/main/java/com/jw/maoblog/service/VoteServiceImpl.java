package com.jw.maoblog.service;

import com.jw.maoblog.domain.Vote;
import com.jw.maoblog.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Vote Service
 */
@Service
public class VoteServiceImpl implements VoteService {

    @Autowired
    private VoteRepository voteRepository;

    @Override
    @Transactional
    public void removeVote(Long id) {
        voteRepository.delete(id);
    }

    @Override
    public Vote getVoteById(Long id) {
        return voteRepository.findOne(id);
    }

}