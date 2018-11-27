package org.wingsico.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.wingsico.bookstore.domain.BookBrief;
import org.wingsico.bookstore.service.BookService;
import org.wingsico.bookstore.domain.Book;
import org.wingsico.bookstore.status.Status;

import java.util.*;

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
     *
     */
    @GetMapping(value = {"", "/"})
    public Status getBookList(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size
    ) {
        Status bookStatus = new Status();
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        Pageable pageable = PageRequest.of(page, size, sort);
        try {
            List<Book> books = bookService.findAll(pageable).getContent();
            bookStatus.setStatus(200);
            bookStatus.setMessage("成功");
            Map<String, Object> map = new HashMap<>();
            map.put("books", books);
            bookStatus.setData(map);
        }catch (NullPointerException ex){ }
        return bookStatus;
    }

    /**
     * 获取Book简要信息列表
     *
     */
    @GetMapping(value = "/brief")
    public Status getBookBriefList(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size
    ){
        Status bookStatus = new Status();
        Sort sort = new Sort(Sort.Direction.ASC,"id");
        Pageable pageable = PageRequest.of(page, size, sort);
        List<BookBrief> bookBriefs = new ArrayList<>();
        try {
            List<Book> books = bookService.findAll(pageable).getContent();
            for(Book book:books){
                BookBrief bookBrief = new BookBrief();
                bookBrief.setId(book.getId());
                bookBrief.setAuthor(book.getAuthor());
                bookBrief.setTitle(book.getTitle());
                bookBrief.setCover_url(book.getCover_url());
                bookBrief.setContent(book.getContent());
                bookBriefs.add(bookBrief);
            }
            bookStatus.setStatus(200);
            bookStatus.setMessage("成功");
            Map<String, Object> map = new HashMap<>();
            map.put("books", bookBriefs);
            bookStatus.setData(map);
        }catch (NullPointerException ex){}
        return bookStatus;
    }
}

