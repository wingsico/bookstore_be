package org.wingsico.bookstore.service;

import org.wingsico.bookstore.domain.Commodity;

import java.util.List;

public interface CommodityService {
    /**
     * 获取该订单商品
     *
     */
    List<Commodity> findAll();

    /**
     * 增加商品
     *
     */
    void addCommodity(int bookID);

    /**
     * 删除商品
     *
     */
    void deleteCommodity(int id);

    /**
     * 修改单个商品数量
     *
     */
    void modifyNumber(int id,int number);
}
