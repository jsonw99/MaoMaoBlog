package com.jw.maoblog.repository;


import com.jw.maoblog.domain.Catalog;
import com.jw.maoblog.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.jw.maoblog.domain.Blog;

/**
 * Blog repository interface
 */
public interface BlogRepository extends JpaRepository<Blog, Long> {
    /**
     * return the blogs of the specific user, with titles like the given keyword.
     *
     * @param user
     * @param title
     * @param pageable
     * @return
     */
    Page<Blog> findByUserAndTitleLike(User user, String title, Pageable pageable);

    /**
     * return the blogs of the specific user, with titles like the given keyword, or with the tags like the given keyword.
     * result sort by descending order in create time.
     *
     * @param title
     * @param user
     * @param tags
     * @param user2
     * @param pageable
     * @return
     */
    Page<Blog> findByTitleLikeAndUserOrTagsLikeAndUserOrderByCreateTimeDesc(String title, User user, String tags, User user2, Pageable pageable);

    /**
     * return the blog results matches someone's catalog.
     *
     * @param catalog
     * @param pageable
     * @return
     */
    Page<Blog> findByCatalog(Catalog catalog, Pageable pageable);
}
