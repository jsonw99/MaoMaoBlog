package com.jw.maoblog.service;

import com.jw.maoblog.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

public interface UserService {
    /**
     * save the user.
     * @param user
     * @return
     */
    User saveOrUpdateUser(User user);

    /**
     * register the user.
     * @param user
     * @return
     */
    User registerUser(User user);

    /**
     * remove the user.
     * @param id
     */
    void removeUser(Long id);

    /**
     * return the user that matches the given id.
     * @param id
     * @return
     */
    User getUserById(Long id);

    /**
     * fuzzy search on the user's name, return pageable result.
     * @param name
     * @param pageable
     * @return
     */
    Page<User> listUsersByNameLike(String name, Pageable pageable);
}
