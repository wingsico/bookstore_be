package org.wingsico.bookstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wingsico.bookstore.domain.Book;
import org.wingsico.bookstore.domain.Commodity;
import org.wingsico.bookstore.domain.repo.CommodityRepo;
import org.wingsico.bookstore.primarykey.UserCommodity;
import org.wingsico.bookstore.service.BookService;
import org.wingsico.bookstore.service.CommodityService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Commodity 业务层实现
 *
 */
@Service
public class CommodityServiceImpl implements CommodityService{
    @Autowired
    CommodityRepo commodityRepo;
    @Autowired
    BookService bookService;
    @Autowired
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Commodity> findAll(){ return commodityRepo.findAll(); }

    @Override
    public List<Commodity> findOrderCommodities(int userID){
        List<Commodity> commodities = new ArrayList<>();
        try{
            List<Commodity> allCommodities = findAll();
            for(int i=0;i<allCommodities.size();i++){
                if(allCommodities.get(i).getUserID()==userID){
                    commodities.add(allCommodities.get(i));
                }
            }
            return commodities;
        }catch (NullPointerException ex){}
        return commodities;
    }

    @Override
    public Commodity addCommodity(int userID, int bookID, int number) {
        Book book = bookService.findOne(bookID);
        Commodity commodity = new Commodity();
        commodity.setBookID(bookID);
        commodity.setUserID(userID);
        commodity.setTitle(book.getTitle());
        commodity.setPrice(book.getPrice());
        commodity.setClassification(book.getClassification());
        commodity.setCover_url(book.getCover_url());
        commodity.setNumber(number);
        commodityRepo.save(commodity);
        return commodity;
    }

    @Override
    public void deleteCommodity(int userID, int bookID){
        Commodity commodity = query(userID, bookID);
        commodityRepo.delete(commodity);
    }

    @Override
    public Commodity modifyNumber(int userID, int bookID, int number){
        Commodity commodity = query(userID, bookID);
        commodity.setNumber(number);
        commodityRepo.save(commodity);
        return commodity;
    }

    @Override
    public Commodity query(int userID, int bookID){
        UserCommodity userCommodity = new UserCommodity();
        userCommodity.setUserID(userID);
        userCommodity.setBookID(bookID);
        Commodity commodity = entityManager.find(Commodity.class, userCommodity);
        return commodity;
    }
}
