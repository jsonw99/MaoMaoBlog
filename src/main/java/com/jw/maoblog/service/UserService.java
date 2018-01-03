package com.jw.maoblog.service;

import com.jw.maoblog.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

/**
 * User Service interface.
 */
public interface UserService {
    /**
     * save the user.
     *
     * @param user
     * @return
     */
    User saveUser(User user);

    /**
     * remove the user.
     *
     * @param id
     */
    void removeUser(Long id);

    /**
     * remove the users within the given list.
     * @param users
     */
    void removeUsersInBatch(List<User> users);

    /**
     * update the user.
     * @param user
     * @return
     */
    User updateUser(User user);

    /**
     * get user by userId
     * @param id
     * @return
     */
    User getUserById(Long id);

    /**
     * return the list of all users
     * @return
     */
    List<User> listUsers();

    /**
     * return the list of users based on the fuzzy search query.
     * @param name
     * @param pageable
     * @return
     */
    Page<User> listUsersByNameLike(String name, Pageable pageable);

    /**
     * return the list of users matches the given usernames collection.
     * @param usernames
     * @return
     */
    List<User> listUsersByUsernames(Collection<String> usernames);
}
