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
     * 根据id获取用户
     * @param id
     * @return
     */
    User getUserById(Long id);

    /**
     * 获取用户列表
     * @return
     */
    List<User> listUsers();

    /**
     * 根据用户名进行分页模糊查询
     * @param name
     * @param pageable
     * @return
     */
    Page<User> listUsersByNameLike(String name, Pageable pageable);

    /**
     * 更具名称列表查询
     * @param usernames
     * @return
     */
    List<User> listUsersByUsernames(Collection<String> usernames);
}
