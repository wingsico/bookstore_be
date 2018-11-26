package org.wingsico.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.wingsico.bookstore.service.BookService;
import org.wingsico.bookstore.domain.Book;

import javax.xml.crypto.Data;
import java.sql.Timestamp;
import java.util.Date;
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
    public List<Book> getBookList(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size
    ) {
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        Pageable pageable = PageRequest.of(page, size, sort);
        return bookService.findAll(pageable).getContent();
    }

    /**
     * 进行增加书籍
     *
     */
    @GetMapping(value = "/insert")
    public String insertUser(@ModelAttribute("title") String title, @ModelAttribute("cover_url") String cover_url,
                             @ModelAttribute("price") float price, @ModelAttribute("author") String author,
                             @ModelAttribute("publish_date") Timestamp publish_date, @ModelAttribute("press") String press,
                             @ModelAttribute("content") String content, @ModelAttribute("author_intro") String author_intro,
                             @ModelAttribute("classification") int classfication){
        Book book = new Book();
        book.setTitle(title);
        book.setCover_url(cover_url);
        book.setPrice(price);
        book.setAuthor(author);
        book.setPublish_date(publish_date);
        book.setPress(press);
        book.setContent(content);
        book.setAuthor_intro(author_intro);
        book.setClassification(classfication);
        bookService.addBook(book);
        return "加入成功";
    }

    /**
     * 进行删除用户
     * @param id
     *
     */
    @GetMapping(value = "/delete/{id}")
    public String deleteUser(@PathVariable("id") int id){
        Book book = new Book();
        book.setId(id);
        bookService.deleteBook(book);
        return "删除成功";
    }

    /**
     * 进行更新用户
     * @param id
     */
    @GetMapping(value = "/update{id}")
    public String updateUser(@PathVariable("id") int id, @ModelAttribute("title") String title,
                             @ModelAttribute("cover_url") String cover_url, @ModelAttribute("price") float price,
                             @ModelAttribute("author") String author, @ModelAttribute("publish_date") Timestamp publish_date,
                             @ModelAttribute("press") String press, @ModelAttribute("content") String content,
                             @ModelAttribute("author_intro") String author_intro, @ModelAttribute("classification") int classfication) {
        Book book = new Book();
        book.setTitle(title);
        book.setCover_url(cover_url);
        book.setPrice(price);
        book.setAuthor(author);
        book.setPublish_date(publish_date);
        book.setPress(press);
        book.setContent(content);
        book.setAuthor_intro(author_intro);
        book.setClassification(classfication);
        bookService.updataBook(book);
        return "更新成功";
    }
}

