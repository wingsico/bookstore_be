package org.wingsico.bookstore.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.wingsico.bookstore.domain.User;

import java.util.List;

public interface UserService {
    /**
     * 获取所有的用户
     *
     */
    List<User> findall();

    /**
     * 新增用户
     *
     */
    void insertUser(User user);

    /**
     * 删除用户
     *
     */
    void deleteUser(User user);

    /**
     * 更新用户
     *
     */
    void updataUser(User user);
}
