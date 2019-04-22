package org.wingsico.bookstore.web;

import com.jcraft.jsch.SftpException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.wingsico.bookstore.domain.*;
import org.wingsico.bookstore.service.BookService;
import org.wingsico.bookstore.service.BusinessService;
import org.wingsico.bookstore.service.UserService;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/register")
public class BusinessController {

    @Resource
    private UserService userService;

    @Resource
    private BookService bookService;

    @Resource
    private BusinessService businessService;

    /**
     * 注册商家
     *
     * @param username
     * @param nickname
     * @param password
     * @param payment
     *
     */
    @PostMapping(value = "/register")
    public ResponseEntity<Map<String,Object>> registerUser(@Valid @RequestBody User user, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("status", 400);
            map.put("message", bindingResult.getFieldError().getDefaultMessage());
            return new ResponseEntity<Map<String,Object>>(map, HttpStatus.BAD_REQUEST);
        }
        user.setDeposit(1000);
        user.setRole("business");
        userService.insertUser(user);
        UserShow userShow = new UserShow();
        userShow.setId(user.getId());
        userShow.setUsername(user.getUsername());
        userShow.setNickname(user.getNickname());
        userShow.setDeposit(user.getDeposit());
        userShow.setRole(user.getRole());
        Map<String, Object> map = new HashMap<>();
        map.put("status", 200);
        map.put("message", "成功");
        Map<String, Object> newMap = new HashMap<>();
        newMap.put("user", userShow);
        map.put("data", newMap);
        return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
    }


    /**
     * 增加书籍
     *
     */
    @PostMapping(value = "/addBooks")
    public ResponseEntity<Map<String,Object>> addBooks(@RequestHeader("Authorization") int userID, BookInput bookInput){
        Map<String, Object> map = new HashMap<>();
        map.put("status", 200);
        map.put("message", "成功");
        int maxID = 0;
        try {
            List<Book> books = bookService.findAllBooks();
            for(int i=0;i<books.size();i++){
                if(books.get(i).getId() > maxID){
                    maxID = books.get(i).getId();
                }
            }
        }catch (NullPointerException ex){}
        Book book = new Book();
        book.setId(maxID+1);
        book.setClassification(bookInput.getClassification());
        book.setAuthor_intro(bookInput.getAuthor_intro());
        book.setAuthor(bookInput.getAuthor());
        book.setContent(bookInput.getContent());
        book.setPress(bookInput.getPress());
        Timestamp timestamp = Timestamp.valueOf(bookInput.getPublish_date());
        book.setPublish_date(timestamp);
        book.setPrice(bookInput.getPrice());
        book.setTitle(bookInput.getTitle());
        MultipartFile file = bookInput.getCover_url();
        try{
            byte[] bytes = file.getBytes();
            String name = file.getOriginalFilename();
            FileConverse fileConverse = new FileConverse();
            fileConverse.login();
            fileConverse.upload(name, bytes);
            String url = fileConverse.url();
            fileConverse.logout();
            book.setCover_url(url);
        }
        catch (SftpException e){e.printStackTrace();}
        catch (IOException ex){ex.printStackTrace();}
        businessService.setBusiness(userID, book.getId());
        Book bookAdd = bookService.addBook(book);
        Map<String, Object> newMap = new HashMap<>();
        newMap.put("book", bookAdd);
        map.put("data",newMap);
        return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
    }
}
