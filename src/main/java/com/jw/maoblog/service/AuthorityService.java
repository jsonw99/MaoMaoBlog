package com.jw.maoblog.service;

import com.jw.maoblog.domain.Authority;

/**
 * Authority Service interface
 */
public interface AuthorityService {

    /**
     * get the Authority by id
     *
     * @param id
     * @return
     */
    Authority getAuthorityById(Long id);
}
