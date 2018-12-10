package org.wingsico.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.wingsico.bookstore.domain.PayOrder;
import org.wingsico.bookstore.domain.User;
import org.wingsico.bookstore.domain.UserShow;
import org.wingsico.bookstore.service.UserService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User控制层
 *
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 注册用户
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
            return new ResponseEntity<Map<String,Object>>(map,HttpStatus.BAD_REQUEST);
        }
        user.setDeposit(1000);
        user.setRole("normal");
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
     * 用户登录
     *
     * @param username
     * @param password
     *
     */
    @PostMapping(value = "/login")
    public ResponseEntity<Map<String,Object>> loginUser(@RequestBody User user){
        Map<String, Object> map = new HashMap<>();
        try {
            List<User> users = userService.findAll();
            for (User userFind:users){
                if(userFind.getUsername().equals(user.getUsername())&&userFind.getPassword().equals(user.getPassword())){
                    UserShow userShow = new UserShow();
                    userShow.setId(userFind.getId());
                    userShow.setUsername(userFind.getUsername());
                    userShow.setNickname(userFind.getNickname());
                    userShow.setDeposit(userFind.getDeposit());
                    userShow.setRole(userFind.getRole());
                    Map<String, Object> userMap = new HashMap<>();
                    userMap.put("user", userShow);
                    map.put("status", 200);
                    map.put("message", "成功");
                    map.put("data", userMap);
                    return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
                }
            }
        }catch (NullPointerException ex){}
        map.put("status", 403);
        map.put("message", "用户名或密码错误");
        return new ResponseEntity<Map<String,Object>>(map,HttpStatus.FORBIDDEN);
    }

    /**
     * 修改用户昵称
     *
     * @param userID
     * @param nickname
     *
     */
    @PostMapping(value = "/update")
    public ResponseEntity<Map<String,Object>> updateUser(@RequestHeader("Authorization") int userID, @RequestBody User user) {
        User userNew = new User();
        userNew.setId(userID);
        userNew.setNickname(user.getNickname());
        User userFind = userService.updateUser(userNew);
        UserShow userShow = new UserShow();
        userShow.setId(userFind.getId());
        userShow.setUsername(userFind.getUsername());
        userShow.setNickname(userFind.getNickname());
        userShow.setDeposit(userFind.getDeposit());
        userShow.setRole(userFind.getRole());
        Map<String, Object> map = new HashMap<>();
        map.put("status", 200);
        map.put("message", "成功");
        Map<String, Object> newMap = new HashMap<>();
        newMap.put("user", userShow);
        map.put("data", newMap);
        return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
    }

    /**
     * 返回用户余额
     *
     * @param userID
     *
     */
    @GetMapping(value = "/deposit")
    public ResponseEntity<Map<String,Object>> getDeposit(@RequestHeader("Authorization") int userID){
        User user = userService.query(userID);
        Map<String, Object> map = new HashMap<>();
        map.put("status", 200);
        map.put("message", "成功");
        Map<String, Object> newMap = new HashMap<>();
        newMap.put("deposit", user.getDeposit());
        map.put("data", newMap);
        return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
    }

    /**
     * 修改支付密码
     *
     * @param userID
     * @param payment
     *
     */
    @PostMapping(value = "updatePayment")
    public ResponseEntity<Map<String,Object>> updatePayment(@RequestHeader("Authorization") int userID, @Valid @RequestBody PayOrder payOrder, BindingResult bindingResult){
        Map<String, Object> map = new HashMap<>();
        if (bindingResult.hasErrors()){
            map.put("status", 400);
            map.put("message", bindingResult.getFieldError().getDefaultMessage());
            return new ResponseEntity<Map<String,Object>>(map,HttpStatus.BAD_REQUEST);
        }
        User userFind = userService.updatePayment(userID, payOrder.getPayment());
        map.put("status", 200);
        map.put("message", "成功");
        return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
    }
}
