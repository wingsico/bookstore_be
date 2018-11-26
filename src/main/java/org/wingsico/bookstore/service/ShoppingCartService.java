package org.wingsico.bookstore.service;

import org.wingsico.bookstore.domain.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {
    /**
     * 获取所有订单
     *
     */
    public List<ShoppingCart> findall();

    /**
     * 进行订单插入
     *
     */
    public void insertShoppingCart(ShoppingCart shoppingCart);

    /**
     * 进行订单删除
     *
     */
    public void deleteShoppingCart(ShoppingCart shoppingCart);
}
