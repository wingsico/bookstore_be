package org.wingsico.bookstore.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Book> findAll() { return bookRepo.findAll(); }

    @Override
    public Book insertByBook(Book book) {
        return bookRepo.save(book);
    }

    @Override
    public Book update(Book book) {
        return bookRepo.save(book);
    }

    @Override
    public Book delete(int id) {
        Book book = bookRepo.findById(id).get();
        bookRepo.delete(book);
        return book;
    }

    @Override
    public Book findById(int id) {
        return bookRepo.findById(id).get();
    }
}
