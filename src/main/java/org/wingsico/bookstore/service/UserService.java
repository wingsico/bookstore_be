package org.wingsico.bookstore.service;


import org.wingsico.bookstore.domain.User;

import java.util.List;

public interface UserService {
    /**
     * 获取所有的用户
     *
     */
    List<User> findAll();

    /**
     * 新增用户
     *
     */
    void insertUser(User user);


    /**
     * 修改昵称
     *
     */
    User updateUser(User user);

}
