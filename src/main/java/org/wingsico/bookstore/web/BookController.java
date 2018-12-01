package org.wingsico.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.wingsico.bookstore.domain.BookBrief;
import org.wingsico.bookstore.domain.BookShow;
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
     * 获取Book简要信息列表
     *
     */
    @GetMapping(value = {"", "/"})
    public Status getBookBriefList(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
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
                bookBrief.setContent(book.getContent().substring(0,30)+"......");
                bookBrief.setPrice(book.getPrice());
                bookBriefs.add(bookBrief);
            }
            List<Book> noPageAllBooks = bookService.findNoPageAll();
            BookShow bookShow = new BookShow();
            bookShow.setNowPage(page + 1);
            bookShow.setPageSize(size);
            bookShow.setBooks(bookBriefs);
            bookShow.setTotalPage((int)Math.ceil(noPageAllBooks.size()*0.1*10/size)+1);
            bookStatus.setStatus(200);
            bookStatus.setMessage("成功");
            Map<String, Object> map = new HashMap<>();
            map.put("page", bookShow);
            bookStatus.setData(map);
        }catch (NullPointerException ex){}
        return bookStatus;
    }

    /**
     * 获取单本书的详细信息
     *
     * @param id
     *
     */
    @PostMapping(value = "/detail")
    public Status getBookDetail(@RequestBody Book book){
        Status status = new Status();
        status.setStatus(200);
        status.setMessage("成功");
        Book bookFind = bookService.findOne(book.getId());
        Map<String, Object> map = new HashMap<>();
        map.put("book", bookFind);
        status.setData(map);
        return status;
    }
}

