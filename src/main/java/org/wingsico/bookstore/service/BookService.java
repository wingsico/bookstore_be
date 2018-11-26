package org.wingsico.bookstore.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.wingsico.bookstore.domain.Book;

import java.util.List;

public interface BookService {
    /**
     * 获取所有 Book
     */
    Page<Book> findAll(Pageable pageable);

    /**
     * 增加书籍
     */
    void addBook(Book book);

    /**
     * 删除书籍
     */
    void deleteBook(Book book);

    /**
     * 更新书籍信息
     */
    void updataBook(Book book);
}
