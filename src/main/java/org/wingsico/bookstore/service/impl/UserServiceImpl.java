package org.wingsico.bookstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.wingsico.bookstore.domain.User;
import org.wingsico.bookstore.domain.repo.UserRepo;
import org.wingsico.bookstore.service.UserService;

import java.util.List;
import java.util.Optional;

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
    public User updateUser(User user){
        Optional<User> userOptional = userRepo.findById(user.getId());
        User userFind = userOptional.get();
        userFind.setNickname(user.getNickname());
        userRepo.save(userFind);
        return userFind;
    }

    @Override
    public void updateDeposit(int userID, float deposit){
        User user = userRepo.getOne(userID);
        user.setDeposit(deposit);
        userRepo.save(user);
    }

    @Override
    public User updatePayment(int userID, String payment){
        User user = userRepo.getOne(userID);
        user.setPayment(payment);
        userRepo.save(user);
        return user;
    }

    @Override
    public User query(int userID){
        return userRepo.getOne(userID);
    }
}
