package org.wingsico.bookstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.wingsico.bookstore.domain.Book;
import org.wingsico.bookstore.domain.Commodity;
import org.wingsico.bookstore.domain.repo.CommodityRepo;
import org.wingsico.bookstore.service.BookService;
import org.wingsico.bookstore.service.CommodityService;

import java.util.List;

/**
 * Commodity 业务层实现
 *
 */
public class CommodityServiceImpl implements CommodityService{
    @Autowired
    CommodityRepo commodityRepo;
    @Autowired
    BookService bookService;

    @Override
    public List<Commodity> findAll(){ return commodityRepo.findAll(); }

    @Override
    public void addCommodity(int bookID) {
        Book book = bookService.findOne(bookID);
        Commodity commodity = new Commodity();
        commodity.setBookID(bookID);
        commodity.setBookTitle(book.getTitle());
        commodity.setBookPrice(book.getPrice());
        commodityRepo.save(commodity);
    }

    @Override
    public void deleteCommodity(int commodityID){
        Commodity commodity = commodityRepo.getOne(commodityID);
        commodityRepo.delete(commodity);
    }

    @Override
    public void modifyNumber(int commodityID,int number){
        Commodity commodity = commodityRepo.getOne(commodityID);
        commodity.setNumber(number);
        commodityRepo.save(commodity);
    }
}
