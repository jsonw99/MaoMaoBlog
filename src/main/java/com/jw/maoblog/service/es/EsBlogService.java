package com.jw.maoblog.service.es;

import com.jw.maoblog.domain.User;
import com.jw.maoblog.domain.es.EsBlog;
import com.jw.maoblog.vo.TagVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * EsBlog Service interface
 */
public interface EsBlogService {

    /**
     * delete EsBlog.
     *
     * @param id
     * @return
     */
    void removeEsBlog(String id);

    /**
     * update EsBlog.
     *
     * @param esBlog
     * @return
     */
    EsBlog updateEsBlog(EsBlog esBlog);

    /**
     * get EsBlog by blogId.
     *
     * @param blogId
     * @return
     */
    EsBlog getEsBlogByBlogId(Long blogId);

    /**
     * list of newest EsBlogs, with pageable result.
     *
     * @param keyword
     * @param pageable
     * @return
     */
    Page<EsBlog> listNewestEsBlogs(String keyword, Pageable pageable);

    /**
     * list of hottest EsBlogs, with pageable result.
     *
     * @param keyword
     * @param pageable
     * @return
     */
    Page<EsBlog> listHotestEsBlogs(String keyword, Pageable pageable);

    /**
     * list of all EsBlogs, with pageable result.
     *
     * @param pageable
     * @return
     */
    Page<EsBlog> listEsBlogs(Pageable pageable);

    /**
     * top5 newest EsBlog.
     *
     * @return
     */
    List<EsBlog> listTop5NewestEsBlogs();

    /**
     * top5 hottest EsBlogs.
     *
     * @return
     */
    List<EsBlog> listTop5HotestEsBlogs();

    /**
     * top30 tags.
     *
     * @return
     */
    List<TagVO> listTop30Tags();

    /**
     * top12 users.
     *
     * @return
     */
    List<User> listTop12Users();
}
