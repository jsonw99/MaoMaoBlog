package com.jw.maoblog.service;

import com.jw.maoblog.domain.Blog;
import com.jw.maoblog.domain.Catalog;
import com.jw.maoblog.domain.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BlogService {
    /**
     * save the blog.
     *
     * @param blog
     * @return
     */
    Blog saveBlog(Blog blog);

    /**
     * detele the blog.
     *
     * @param id
     */
    void removeBlog(Long id);

    /**
     * return the Blog by blogId.
     *
     * @param id
     * @return
     */
    Blog getBlogById(Long id);

    /**
     * fuzzy search, find the Blogs with title or tag match the given keyword, for the specific user.
     *
     * @param user
     * @return
     */
    Page<Blog> listBlogsByTitleTag(User user, String title, Pageable pageable);

    /**
     * fuzzy search, find the Blogs with title match the given keyword, for the specific user.
     *
     * @param user
     * @return
     */
    Page<Blog> listBlogsByTitleAndSort(User user, String title, Pageable pageable);

    /**
     * find blogs based on Catalog for the corresponding user
     *
     * @param catalog
     * @param pageable
     * @return
     */
    Page<Blog> listBlogsByCatalog(Catalog catalog, Pageable pageable);

    /**
     * increase the number of visit.
     *
     * @param id
     */
    void readingIncrease(Long id);

    /**
     * make the Comment.
     *
     * @param blogId
     * @param commentContent
     * @return
     */
    Blog createComment(Long blogId, String commentContent);

    /**
     * delete the Comment.
     *
     * @param blogId
     * @param commentId
     * @return
     */
    void removeComment(Long blogId, Long commentId);

    /**
     * create the Vote (like).
     *
     * @param blogId
     * @return
     */
    Blog createVote(Long blogId);

    /**
     * remove the Vote.
     *
     * @param blogId
     * @param voteId
     * @return
     */
    void removeVote(Long blogId, Long voteId);
}
