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
     * 获取该订单的全部商品
     *
     */
    List<Commodity> findOrderCommodities(int orderID);

    /**
     * 订单中增加商品
     *
     */
    void addCommodity(int orderID, int id);

    /**
     * 订单中删除商品
     *
     */
    void deleteCommodity(int orderID, int commodityID);

    /**
     * 修改单个商品数量
     *
     */
    void modifyNumber(int orderID, int commodityID, int number);
}
