package org.wingsico.bookstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wingsico.bookstore.domain.Order;
import org.wingsico.bookstore.domain.OrderBooks;
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
    public OrderBooks addOrder(int userID, ArrayList<Integer> bookIDs){
        OrderBooks orderBooks = new OrderBooks();
        int orderUsedID = 0;
        try{
            List<Order> allOrder = findAll();
            for(int i=0;i<allOrder.size();i++){
                if(orderUsedID<allOrder.get(i).getOrderID()){
                    orderUsedID = allOrder.get(i).getOrderID();
                }
            }
        }catch (NullPointerException ex){}
        int orderID = orderUsedID + 1;
        Date date = new Date();
        Timestamp nowdate = new Timestamp(date.getTime());
        for(int i=0;i<bookIDs.size();i++){
            Order order = new Order();
            order.setOrderID(orderID);
            order.setStatus(1);
            order.setDate(nowdate);
            order.setUserID(userID);
            order.setBookID(bookIDs.get(i));
            orderRepo.save(order);
        }
        orderBooks.setBookIDs(bookIDs);
        orderBooks.setDate(nowdate);
        orderBooks.setOrderID(orderID);
        orderBooks.setStatus(1);
        orderBooks.setUserID(userID);
        return orderBooks;
    }

    @Override
    public void deleteOrder(int orderID){
        try {
            List<Order> orders = findAll();
            for(int i=0;i<orders.size();i++){
                if(orders.get(i).getOrderID()==orderID){
                    orderRepo.delete(orders.get(i));
                }
            }
        }catch (NullPointerException ex){}
    }

    @Override
    public void modifyStatus(int orderID, int status){
        try {
            List<Order> orders = findAll();
            for(int i=0;i<orders.size();i++){
                if(orders.get(i).getOrderID()==orderID){
                    orders.get(i).setStatus(status);
                    orderRepo.save(orders.get(i));
                }
            }
        }catch (NullPointerException ex){}
    }

    @Override
    public Order query(int orderID){
        Order order = new Order();
        try {
            List<Order> orders = findAll();
            for (int i=0;i<orders.size();i++){
                if(orders.get(i).getOrderID() == orderID){
                    return orders.get(i);
                }
            }
        }catch (NullPointerException ex){}
        return order;
    }
}
