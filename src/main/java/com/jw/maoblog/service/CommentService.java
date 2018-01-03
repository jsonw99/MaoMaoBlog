package com.jw.maoblog.service;

import com.jw.maoblog.domain.Comment;

/**
 * Comment Service interface
 */
public interface CommentService {
    /**
     * get Comment by commontId
     *
     * @param id
     * @return
     */
    Comment getCommentById(Long id);

    /**
     * delete comment
     *
     * @param id
     * @return
     */
    void removeComment(Long id);
}
