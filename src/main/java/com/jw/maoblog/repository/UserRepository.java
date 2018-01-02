package com.jw.maoblog.repository;

import com.jw.maoblog.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

/**
 * User Repository interface
 */
public interface UserRepository extends JpaRepository<User, Long>{
    /**
     * fuzzy search on the user's name, return pageable result.
     * @param name
     * @param pageable
     * @return
     */
    Page<User> findByNameLike(String name, Pageable pageable);

    /**
     * return the user match the given username.
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * return user list matches the usernames collection.
     * @param usernames
     * @return
     */
    List<User> findByUsernameIn(Collection<String> usernames);
}
