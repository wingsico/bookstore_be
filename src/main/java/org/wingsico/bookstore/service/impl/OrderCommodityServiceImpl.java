package org.wingsico.bookstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wingsico.bookstore.domain.Book;
import org.wingsico.bookstore.domain.Commodity;
import org.wingsico.bookstore.domain.OrderCommodity;
import org.wingsico.bookstore.domain.repo.OrderCommodityRepo;
import org.wingsico.bookstore.primarykey.UserCommodity;
import org.wingsico.bookstore.service.BookService;
import org.wingsico.bookstore.service.CommodityService;
import org.wingsico.bookstore.service.OrderCommodityService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

/**
 * OrderCommodity 业务层实现
 *
 */
@Service
public class OrderCommodityServiceImpl implements OrderCommodityService{
    @Autowired
    OrderCommodityRepo orderCommodityRepo;
    @Autowired
    BookService bookService;
    @Autowired
    @PersistenceContext
    EntityManager entityManager;
    @Autowired
    CommodityService commodityService;

    @Override
    public List<OrderCommodity> findAll(){ return orderCommodityRepo.findAll(); }

    @Override
    public OrderCommodity addCommodity(int userID, int bookID) {
        Commodity commodity = commodityService.query(userID, bookID);
        OrderCommodity orderCommodity = new OrderCommodity();
        orderCommodity.setBookID(bookID);
        orderCommodity.setUserID(userID);
        orderCommodity.setTitle(commodity.getTitle());
        orderCommodity.setPrice(commodity.getPrice());
        orderCommodity.setCover_url(commodity.getCover_url());
        orderCommodity.setClassification(commodity.getClassification());
        orderCommodity.setNumber(commodity.getNumber());
        orderCommodityRepo.save(orderCommodity);
        return orderCommodity;
    }

    @Override
    public OrderCommodity query(int userID, int bookID){
        UserCommodity userCommodity = new UserCommodity();
        userCommodity.setUserID(userID);
        userCommodity.setBookID(bookID);
        OrderCommodity orderCommodity = entityManager.find(OrderCommodity.class, userCommodity);
        return orderCommodity;
    }
}
