package org.wingsico.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.wingsico.bookstore.service.BookService;
import org.wingsico.bookstore.domain.Book;

import java.util.List;

/**
 * Book 控制层
 *
 */
@RestController
@RequestMapping(value = "/book")
public class BookController {


    @Autowired
    BookService bookService;

    /**
     * 获取 Book 列表
     * 处理 "/book" 的 GET 请求，用来获取 Book 列表
     */
    @GetMapping(value = {"", "/"})
    public List<Book> getBookList(ModelMap map) {
        return bookService.findAll();
    }




    /**
     * 删除 Book
     * 处理 "/book/{id}" 的 GET 请求，用来删除 Book 信息
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable int id) {
        bookService.delete(id);
        return "删除成功";
    }

}

