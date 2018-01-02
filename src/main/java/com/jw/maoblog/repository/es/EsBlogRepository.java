package com.jw.maoblog.repository.es;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.jw.maoblog.domain.es.EsBlog;

/**
 * EsBlog (Elastic Search Blog) Repository interface.
 */
public interface EsBlogRepository extends ElasticsearchRepository<EsBlog, String> {

    /**
     * return the EsBlog results, with title, or summary, or content, or tag match the given keyword.
     * also remove the duplicates from the results before return.
     *
     * @param title
     * @param Summary
     * @param content
     * @param tags
     * @param pageable
     * @return
     */
    Page<EsBlog> findDistinctEsBlogByTitleContainingOrSummaryContainingOrContentContainingOrTagsContaining(String title, String Summary, String content, String tags, Pageable pageable);

    /**
     * return the EsBlog, which match the blogId.
     * @param blogId
     * @return
     */
    EsBlog findByBlogId(Long blogId);
}
