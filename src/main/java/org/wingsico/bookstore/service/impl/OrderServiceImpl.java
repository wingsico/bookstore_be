package org.wingsico.bookstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterUtils;
import org.springframework.stereotype.Service;
import org.wingsico.bookstore.domain.Commodity;
import org.wingsico.bookstore.domain.Order;
import org.wingsico.bookstore.domain.repo.OrderRepo;
import org.wingsico.bookstore.primarykey.CartOrder;
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
                if(allOrders.get(i).getCartID() == userID){
                    orders.add(allOrders.get(i));
                }
            }
            return orders;
        }catch (NullPointerException ex){}
        return orders;
    }

    @Override
    public void addOrder(int cartID, int commodityID){
        Order order = new Order();
        Date date = new Date();
        Timestamp nowdate = new Timestamp(date.getTime());
        order.setStatus(0);
        order.setDate(nowdate);
        order.setCartID(cartID);
        orderRepo.save(order);
        commodityService.addCommodity(order.getOrderID(), commodityID);
    }

    @Override
    public void deleteOrder(int cartID, int orderID){
        CartOrder cartOrder = new CartOrder();
        cartOrder.setCartID(cartID);
        cartOrder.setOrderID(orderID);
        Order order = entityManager.find(Order.class, cartOrder);
        orderRepo.delete(order);
    }

    @Override
    public void modifyStatus(int cartID, int orderID){
        CartOrder cartOrder = new CartOrder();
        cartOrder.setCartID(cartID);
        cartOrder.setOrderID(orderID);
        Order order = entityManager.find(Order.class, cartOrder);
        if(order.getStatus()==1){
            order.setStatus(0);
        }
        else {
            order.setStatus(1);
        }
    }
}
