package org.wingsico.bookstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wingsico.bookstore.domain.Book;
import org.wingsico.bookstore.domain.Commodity;
import org.wingsico.bookstore.domain.repo.CommodityRepo;
import org.wingsico.bookstore.primarykey.OrderCommodity;
import org.wingsico.bookstore.service.BookService;
import org.wingsico.bookstore.service.CommodityService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Commodity 业务层实现
 *
 */
@Service
public class CommodityServiceImpl implements CommodityService{
    @Autowired
    CommodityRepo commodityRepo;
    @Autowired
    BookService bookService;
    @Autowired
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Commodity> findAll(){ return commodityRepo.findAll(); }

    @Override
    public List<Commodity> findOrderCommodities(int orderID){
        List<Commodity> commodities = new ArrayList<>();
        try{
            List<Commodity> allCommodities = findAll();
            for(int i=0;i<allCommodities.size();i++){
                if(allCommodities.get(i).getOrderID()==orderID){
                    commodities.add(allCommodities.get(i));
                }
            }
            return commodities;
        }catch (NullPointerException ex){}
        return commodities;
    }

    @Override
    public void addCommodity(int orderID, int commodityID) {
        Book book = bookService.findOne(commodityID);
        Commodity commodity = new Commodity();
        commodity.setCommodityID(commodityID);
        commodity.setOrderID(orderID);
        commodity.setBookTitle(book.getTitle());
        commodity.setBookPrice(book.getPrice());
        commodity.setNumber(1);
        commodityRepo.save(commodity);
    }

    @Override
    public void deleteCommodity(int orderID, int commodityID){
        OrderCommodity orderCommodity = new OrderCommodity();
        orderCommodity.setOrderID(orderID);
        orderCommodity.setCommodityID(commodityID);
        Commodity commodity = entityManager.find(Commodity.class, orderCommodity);
        commodityRepo.delete(commodity);
    }

    @Override
    public void modifyNumber(int orderID,int commodityID, int number){
        OrderCommodity orderCommodity = new OrderCommodity();
        orderCommodity.setOrderID(orderID);
        orderCommodity.setCommodityID(commodityID);
        Commodity commodity = entityManager.find(Commodity.class, orderCommodity);
        commodity.setNumber(number);
        commodityRepo.save(commodity);
    }
}
