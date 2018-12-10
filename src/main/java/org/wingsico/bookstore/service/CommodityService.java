package org.wingsico.bookstore.service;

import org.wingsico.bookstore.domain.Commodity;

import java.util.List;

public interface CommodityService {
    /**
     * 获取全部商品
     *
     */
    List<Commodity> findAll();

    /**
     * 获取该用户购物车的全部商品
     *
     */
    List<Commodity> findOrderCommodities(int userID);

    /**
     * 购物车中增加商品
     *
     */
    Commodity addCommodity(int userID, int bookID, int number);

    /**
     * 购物车中删除商品
     *
     */
    void deleteCommodity(int userID, int bookID);

    /**
     * 修改单个商品数量
     *
     */
    Commodity modifyNumber(int userID, int bookID, int number);

    /**
     * 查询该用户的该商品
     *
     */
    Commodity query(int userID, int bookID);
}
