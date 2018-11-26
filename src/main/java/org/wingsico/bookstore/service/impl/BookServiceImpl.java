package org.wingsico.bookstore.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.wingsico.bookstore.domain.Book;
import org.wingsico.bookstore.domain.repo.BookRepo;
import org.wingsico.bookstore.service.BookService;

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
    public Page<Book> findAll(Pageable pageable) {
        return bookRepo.findAll(pageable);
    }

    @Override
    public void addBook(Book book){
        bookRepo.save(book);
    }

    @Override
    public void deleteBook(Book book){
        bookRepo.delete(book);
    }

    @Override
    public void updataBook(Book book){
        bookRepo.save(book);
    }
}
