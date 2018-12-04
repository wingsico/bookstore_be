package org.wingsico.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.wingsico.bookstore.domain.BookBrief;
import org.wingsico.bookstore.domain.Classification;
import org.wingsico.bookstore.service.BookService;
import org.wingsico.bookstore.domain.Book;
import org.wingsico.bookstore.service.ClassificationService;
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
    @Autowired
    ClassificationService classificationService;

    /**
     * 获取Book简要信息列表
     *
     * @param classification
     *
     */
    @PostMapping(value = {"", "/"})
    public Status getBookBriefList(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size,
            @RequestBody Book bookIn
    ){
        Status bookStatus = new Status();
        Sort sort = new Sort(Sort.Direction.ASC,"id");
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        List<BookBrief> bookBriefs = new ArrayList<>();
        try {
            List<Book> books = bookService.findAll(bookIn.getClassification(),pageable).getContent();
            for(Book book:books){
                BookBrief bookBrief = new BookBrief();
                bookBrief.setId(book.getId());
                bookBrief.setAuthor(book.getAuthor());
                bookBrief.setTitle(book.getTitle());
                bookBrief.setCover_url(book.getCover_url());
                bookBrief.setClassification(book.getClassification());
                bookBrief.setPrice(book.getPrice());
                bookBriefs.add(bookBrief);
            }
            List<Book> noPageAllBooks = bookService.findNoPageAll(bookIn.getClassification());
            List<Book> allBooks = bookService.findAllBooks();
            bookStatus.setStatus(200);
            bookStatus.setMessage("成功");
            Map<String, Object> map = new HashMap<>();
            map.put("nowPage", page);
            map.put("pageSize", size);
            map.put("totalPage", (int)Math.ceil(noPageAllBooks.size()*0.1*10/size));
            map.put("totalClassification", noPageAllBooks.size());
            map.put("total", allBooks.size());
            map.put("books", bookBriefs);
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

    /**
     * 获取所有的书籍分类
     *
     */
    @GetMapping(value = "/classification")
    public Status getClassification(){
        Status status = new Status();
        status.setStatus(200);
        status.setMessage("成功");
        try {
            List<Classification> classifications = classificationService.findAll();
            Map<String, Object> map = new HashMap<>();
            map.put("classifications", classifications);
            status.setData(map);
            return status;
        }catch (NullPointerException ex) {}
        return status;
    }

    /**
     * 随机获取20本书作为推荐
     *
     */
    @GetMapping(value = "recommend")
    public Status getRecommend(){
        Status status = new Status();
        status.setStatus(200);
        status.setMessage("成功");
        List<BookBrief> books = new ArrayList<>();
        Random random = new Random();
        try{
            List<Book> allBooks = bookService.findAllBooks();
            for(int i=0;i<20;i++){
                int id = random.nextInt(allBooks.size())+1;
                Book book = bookService.findOne(id);
                BookBrief bookBrief = new BookBrief();
                bookBrief.setClassification(book.getClassification());
                bookBrief.setPrice(book.getPrice());
                bookBrief.setCover_url(book.getCover_url());
                bookBrief.setTitle(book.getTitle());
                bookBrief.setAuthor(book.getAuthor());
                bookBrief.setId(book.getId());
                books.add(bookBrief);
            }
        }catch (NullPointerException ex){}
        Map<String, Object> map = new HashMap<>();
        map.put("books", books);
        status.setData(map);
        return status;
    }

    /**
     * 根据输入内容进行模糊查询
     *
     * @param content
     *
     */
    @PostMapping(value = "query")
    public Status getQuery(@RequestBody Book book){
        Status status = new Status();
        status.setStatus(200);
        status.setMessage("成功");
        try {
            List<Book> books = bookService.likeQuery(book.getContent());
            Map<String, Object> map = new HashMap<>();
            map.put("books", books);
            status.setData(map);
            return status;
        }catch (NullPointerException ex){}
        return status;
    }
}

