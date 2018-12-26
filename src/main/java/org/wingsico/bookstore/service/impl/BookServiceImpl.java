package org.wingsico.bookstore.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.wingsico.bookstore.domain.Book;
import org.wingsico.bookstore.domain.repo.BookRepo;
import org.wingsico.bookstore.service.BookService;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Book 业务层实现
 *
 * Created by bysocket on 30/09/2017.
 */
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepo bookRepo;

    @Override
    public Page<Book> findNoClassificationAll(Pageable pageable){
        return bookRepo.findAll(pageable);
    }

    @Override
    public Page<Book> findAll(int classification, Pageable pageable) {
        return bookRepo.findByClassification(classification, pageable);
    }

    @Override
    public List<Book> findNoPageAll(int classification){
        List<Book> books = bookRepo.findByClassification(classification);
        return books;
    }

    @Override
    public int getNumber(){
        return (int)bookRepo.count();
    }

    @Override
    public Book addBook(Book book){ return bookRepo.save(book); }

    @Override
    public List<Book> findAllBooks(){ return bookRepo.findAll(); }

    @Override
    public Book findOne(int id){ return bookRepo.getOne(id); }

    @Override
    public List<Book> likeQuery(String content){
        return bookRepo.findByContent(content);
    }
}
