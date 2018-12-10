package org.wingsico.bookstore.service;

import org.wingsico.bookstore.domain.OrderCommodity;

import java.util.List;

public interface OrderCommodityService {
    /**
     * 获取订单中商品
     *
     */
    List<OrderCommodity> findAll();

    /**
     * 订单中增加商品
     *
     */
    OrderCommodity addCommodity(int userID, int bookID);

    /**
     * 查询该用户的该商品
     *
     */
    OrderCommodity query(int userID, int bookID);
}
