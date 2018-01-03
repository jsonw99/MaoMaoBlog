package com.jw.maoblog.service;

import com.jw.maoblog.domain.Vote;

/**
 * Vote Service interface
 */
public interface VoteService {
	/**
	 * get the Vote by voteId.
	 * @param id
	 * @return
	 */
	Vote getVoteById(Long id);
	/**
	 * remove the vote.
	 * @param id
	 * @return
	 */
	void removeVote(Long id);
}
