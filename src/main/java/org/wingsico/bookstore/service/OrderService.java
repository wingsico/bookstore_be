package org.wingsico.bookstore.service;

import org.wingsico.bookstore.domain.Commodity;
import org.wingsico.bookstore.domain.Order;

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
    Order addOrder(int userID, int bookID);

    /**
     * 删除订单
     *
     */
    void deleteOrder(int orderID);

    /**
     * 修改订单的状态
     *
     */
    Order modifyStatus(int orderID, int status);
}
