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

    /**
     * 修改用户余额
     *
     */
    User updateDeposit(int userID, float deposit);

    /**
     * 修改用户支付密码
     *
     */
    User updatePayment(int userID, String String);
}
