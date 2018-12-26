package org.wingsico.bookstore.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.wingsico.bookstore.domain.Book;

import java.util.List;

public interface BookService {
    /**
     * 获取所有不分类书籍
     *
     */
    Page<Book> findNoClassificationAll(Pageable pageable);

    /**
     * 获取所有分类后书籍
     *
     */
    Page<Book> findAll(int classification, Pageable pageable);

    /**
     * 获取不进行分页的分类后的所有书籍
     *
     */
    List<Book> findNoPageAll(int classification);

    /**
     * 获取书籍的数量
     *
     */
    int getNumber();

    /**
     * 增加书籍
     *
     */
    Book addBook(Book book);

    /**
     * 获取所有书籍
     *
     */
    List<Book> findAllBooks();

    /**
     * 查找单本书籍
     *
     */
    Book findOne(int id);

    /**
     * 书籍信息模糊查询
     *
     */
    List<Book> likeQuery(String content);
}
