package org.wingsico.bookstore.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.wingsico.bookstore.domain.Book;
import org.wingsico.bookstore.domain.BookBrief;

import java.util.List;

public interface BookService {
    /**
     * 获取所有 Book
     *
     */
    Page<Book> findAll(Pageable pageable);

}
