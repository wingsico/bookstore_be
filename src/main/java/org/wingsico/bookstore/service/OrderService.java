package org.wingsico.bookstore.service;

import org.wingsico.bookstore.domain.Commodity;
import org.wingsico.bookstore.domain.Order;
import org.wingsico.bookstore.domain.OrderBooks;

import java.util.ArrayList;
import java.util.List;

public interface OrderService {
    /**
     * 获取所有的订单
     *
     */
    List<Order> findAll();

    /**
     * 获取该用户的所有订单
     *
     */
    List<Order> findUserOrders(int userID);

    /**
     * 增加订单
     *
     */
    OrderBooks addOrder(int userID, ArrayList<Integer> bookIDs);

    /**
     * 删除订单
     *
     */
    void deleteOrder(int orderID);

    /**
     * 修改订单的状态
     *
     */
    void modifyStatus(int orderID, int status);

    Order query(int orderID);
}
