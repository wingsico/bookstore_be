package org.wingsico.bookstore.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.wingsico.bookstore.domain.Book;

import java.util.List;

public interface BookService {
    /**
     * 获取所有 Book
     */
    Page<Book> findAll(Pageable page);

    /**
     * 新增 Book
     *
     * @param book {@link Book}
     */
    Book insertByBook(Book book);

    /**
     * 更新 Book
     *
     * @param book {@link Book}
     */
    Book update(Book book);

    /**
     * 删除 Book
     *
     * @param id 编号
     */
    Book delete(int id);

    /**
     * 获取 Book
     *
     * @param id 编号
     */
    Book findById(int id);
}
