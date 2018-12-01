package org.wingsico.bookstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wingsico.bookstore.domain.Order;
import org.wingsico.bookstore.domain.repo.OrderRepo;
import org.wingsico.bookstore.service.CommodityService;
import org.wingsico.bookstore.service.OrderService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Order 业务层实现
 *
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepo orderRepo;
    @Autowired
    CommodityService commodityService;
    @Autowired
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Order> findAll() { return orderRepo.findAll(); }

    @Override
    public List<Order> findUserOrders(int userID){
        List<Order> orders = new ArrayList<>();
        try{
            List<Order> allOrders = findAll();
            for(int i=0;i<allOrders.size();i++){
                if(allOrders.get(i).getUserID() == userID){
                    orders.add(allOrders.get(i));
                }
            }
            return orders;
        }catch (NullPointerException ex){}
        return orders;
    }

    @Override
    public Order addOrder(int userID, int bookID){
        Order order = new Order();
        Date date = new Date();
        Timestamp nowdate = new Timestamp(date.getTime());
        order.setStatus(0);
        order.setDate(nowdate);
        order.setUserID(userID);
        order.setBookID(bookID);
        orderRepo.save(order);
        commodityService.addCommodity(order.getOrderID(), bookID);
        return order;
    }

    @Override
    public void deleteOrder(int orderID){
        Order order = orderRepo.getOne(orderID);
        orderRepo.delete(order);
    }

    @Override
    public Order modifyStatus(int orderID){
        Order order = orderRepo.getOne(orderID);
        if(order.getStatus()==1){
            order.setStatus(0);
        }
        else {
            order.setStatus(1);
        }
        orderRepo.save(order);
        return order;
    }
}
