package org.wingsico.bookstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.wingsico.bookstore.domain.User;
import org.wingsico.bookstore.domain.repo.UserRepo;
import org.wingsico.bookstore.service.UserService;

import java.util.List;

/**
 * User业务层实现
 *
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepo userRepo;

    @Override
    public List<User> findAll(){ return userRepo.findAll(); }

    @Override
    public void insertUser(User user){ userRepo.save(user); }

    @Override
    public void deleteUser(User user){
        userRepo.delete(user);
    }

    @Override
    public void updataUser(User user){
        userRepo.save(user);
    }
}
