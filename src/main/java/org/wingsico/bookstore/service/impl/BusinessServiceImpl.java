package org.wingsico.bookstore.service.impl;

import org.wingsico.bookstore.domain.Business;
import org.wingsico.bookstore.domain.repo.BusinessRepo;
import org.wingsico.bookstore.service.BusinessService;

import javax.annotation.Resource;

public class BusinessServiceImpl implements BusinessService {

    @Resource
    private BusinessRepo businessRepo;

    @Override
    public Business getBusiness(int bookId){
        return businessRepo.findByBookId(bookId);
    }

    @Override
    public void setBusiness(int userId, int bookId){
        Business business = new Business();
        business.setBookId(bookId);
        business.setUserId(userId);
        businessRepo.save(business);
    }
}
