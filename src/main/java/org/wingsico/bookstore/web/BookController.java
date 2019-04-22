package org.wingsico.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wingsico.bookstore.domain.BookBrief;
import org.wingsico.bookstore.domain.Classification;
import org.wingsico.bookstore.service.*;
import org.wingsico.bookstore.domain.Book;

import javax.annotation.Resource;
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
    @Resource
    private RecommendService recommendService;
    @Resource
    private BusinessService businessService;
    @Resource
    private UserService userService;

    /**
     * 获取所有书籍简要信息列表
     *
     */
    @GetMapping(value = {"", "/"})
    public ResponseEntity<Map<String,Object>> getAllBooks(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size
    ){
        Map<String, Object> map = new HashMap<>();
        Sort sort = new Sort(Sort.Direction.ASC,"id");
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        List<BookBrief> bookBriefs = new ArrayList<>();
        try {
            List<Book> books = bookService.findNoClassificationAll(pageable).getContent();
            for(Book book:books){
                BookBrief bookBrief = new BookBrief();
                bookBrief.setTitle(book.getTitle());
                bookBrief.setCover_url(book.getCover_url());
                bookBrief.setId(book.getId());
                bookBrief.setAuthor(book.getAuthor());
                bookBrief.setClassification(book.getClassification());
                bookBrief.setPrice(book.getPrice());
                if (businessService.getBusiness(book.getId())==null){
                    bookBrief.setBusiness("cura官方");
                }else {
                    bookBrief.setBusiness(userService.query(businessService.getBusiness(book.getId()).getUserId()).getUsername());
                }
                bookBriefs.add(bookBrief);
            }
            map.put("status", 200);
            map.put("message", "成功");
            Map<String, Object> newMap = new HashMap<>();
            newMap.put("books", bookBriefs);
            newMap.put("page", page);
            newMap.put("size", size);
            newMap.put("totalPage", (int)Math.ceil(bookService.getNumber()*0.1*10/size));
            newMap.put("total", bookService.getNumber());
            map.put("data", newMap);
            return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
        }catch (NullPointerException ex){}
        return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
    }

    /**
     * 获取分类简要信息列表
     *
     * @param classification
     *
     */
    @GetMapping(value = "/classificationBooks")
    public ResponseEntity<Map<String,Object>> getBookBriefList(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size,
            @RequestParam int classification
    ){
        Map<String, Object> map = new HashMap<>();
        Sort sort = new Sort(Sort.Direction.ASC,"id");
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        List<BookBrief> bookBriefs = new ArrayList<>();
        try {
            List<Book> books = bookService.findAll(classification,pageable).getContent();
            for(Book book:books){
                BookBrief bookBrief = new BookBrief();
                bookBrief.setId(book.getId());
                bookBrief.setAuthor(book.getAuthor());
                bookBrief.setClassification(book.getClassification());
                bookBrief.setPrice(book.getPrice());
                bookBrief.setTitle(book.getTitle());
                bookBrief.setCover_url(book.getCover_url());
                if (businessService.getBusiness(book.getId())==null){
                    bookBrief.setBusiness("cura官方");
                }else {
                    bookBrief.setBusiness(userService.query(businessService.getBusiness(book.getId()).getUserId()).getUsername());
                }
                bookBriefs.add(bookBrief);
            }
            List<Book> noPageAllBooks = bookService.findNoPageAll(classification);
            map.put("status", 200);
            map.put("message", "成功");
            Map<String, Object> newMap = new HashMap<>();
            newMap.put("page", page);
            newMap.put("size", size);
            newMap.put("totalPage", (int)Math.ceil(noPageAllBooks.size()*0.1*10/size));
            newMap.put("totalClassification", noPageAllBooks.size());
            newMap.put("total", bookService.getNumber());
            newMap.put("books", bookBriefs);
            map.put("data", newMap);
            return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
        }catch (NullPointerException ex){}
        return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
    }

    /**
     * 获取单本书的详细信息
     *
     *
     */
    @PostMapping(value = "/detail")
    public ResponseEntity<Map<String,Object>> getBookDetail(@RequestBody Book book){
        Map<String, Object> map = new HashMap<>();
        map.put("status", 200);
        map.put("message", "成功");
        Book bookFind = bookService.findOne(book.getId());
        String business;
        if (businessService.getBusiness(book.getId())==null){
            business = "cura官方";
        }else {
            business = userService.query(businessService.getBusiness(book.getId()).getUserId()).getUsername();
        }
        Map<String, Object> newMap = new HashMap<>();
        newMap.put("book", bookFind);
        newMap.put("business", business);
        map.put("data", newMap);
        return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
    }

    /**
     * 获取所有的书籍分类
     *
     */
    @GetMapping(value = "/classification")
    public ResponseEntity<Map<String,Object>> getClassification(){
        Map<String, Object> map = new HashMap<>();
        map.put("status", 200);
        map.put("message", "成功");
        try {
            List<Classification> classifications = classificationService.findAll();
            Map<String, Object> newMap = new HashMap<>();
            newMap.put("classifications", classifications);
            map.put("data", newMap);
            return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
        }catch (NullPointerException ex) {}
        return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
    }

    /**
     * 进行书籍推荐
     *
     */
    @GetMapping(value = "/recommend")
    public ResponseEntity<Map<String,Object>> getRecommend(@RequestHeader("Authorization") int userID){
        Map<String, Object> map = new HashMap<>();
        map.put("status", 200);
        map.put("message", "成功");
        List<BookBrief> books = new ArrayList<>();
        Random random = new Random();
        ArrayList<Book> bookAll = recommendService.getRecommend(userID);
        for (Book book:bookAll){
            BookBrief bookBrief = new BookBrief();
            bookBrief.setClassification(book.getClassification());
            bookBrief.setPrice(book.getPrice());
            bookBrief.setCover_url(book.getCover_url());
            bookBrief.setTitle(book.getTitle());
            bookBrief.setAuthor(book.getAuthor());
            bookBrief.setId(book.getId());
            books.add(bookBrief);
        }
        Map<String, Object> newMap = new HashMap<>();
        newMap.put("books", books);
        map.put("data", newMap);
        return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
    }

    /**
     * 根据输入内容进行模糊查询
     *
     *
     */
    @PostMapping(value = "query")
    public ResponseEntity<Map<String,Object>> getQuery(@RequestBody Book book){
        Map<String, Object> map = new HashMap<>();
        map.put("status", 200);
        map.put("message", "成功");
        try {
            List<Book> books = bookService.likeQuery(book.getContent());
            Map<String, Object> newMap = new HashMap<>();
            newMap.put("books", books);
            map.put("data", newMap);
            return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
        }catch (NullPointerException ex){}
        return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
    }

    @PostMapping(value = "test")
    public ResponseEntity<Map<String,Object>> test(@RequestBody Book book){
        Map<String, Object> map = new HashMap<>();
        map.put("status", 200);
        map.put("message", "成功");
        map.put("book", book.getCover_url());
        return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
    }
}

