package org.wingsico.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.wingsico.bookstore.domain.User;
import org.wingsico.bookstore.service.UserService;
import org.wingsico.bookstore.status.Status;

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
     * @param userName
     * @param nickname
     * @param password
     *
     */
    @PostMapping(value = "/register")
    public Status registerUser(@Valid @RequestBody User user, BindingResult bindingResult){
        Status userStatus = new Status();
        if (bindingResult.hasErrors()){
            userStatus.setStatus(404);
            userStatus.setMessage(bindingResult.getFieldError().getDefaultMessage());
            return userStatus;
        }
        user.setDeposit(1000);
        userService.insertUser(user);
        userStatus.setStatus(200);
        userStatus.setMessage("成功");
        Map<String, Object> map = new HashMap<>();
        map.put("user", user);
        userStatus.setData(map);
        return userStatus;
    }

    /**
     * 用户登录
     *
     * @param userName
     * @param password
     *
     */
    @PostMapping(value = "/login")
    public Status loginUser(@RequestBody User user){
        Status userStatus = new Status();
        try {
            List<User> users = userService.findAll();
            for (User userFind:users){
                if(userFind.getUserName().equals(user.getUserName())&&userFind.getPassword().equals(user.getPassword())){
                    userStatus.setStatus(200);
                    userStatus.setMessage("成功");
                    Map<String, Object> map = new HashMap<>();
                    map.put("user", userFind);
                    userStatus.setData(map);
                    return userStatus;
                }
            }
            userStatus.setStatus(404);
            userStatus.setMessage("用户名或密码错误");
            return userStatus;
        }catch (NullPointerException ex){}
        return userStatus;
    }

    /**
     * 修改用户昵称
     *
     * @param id
     * @param nickname
     *
     */
    @PostMapping(value = "/update")
    public Status updateUser(@RequestBody User user, BindingResult bindingResult) {
        Status userStatus = new Status();
        if (bindingResult.hasErrors()){
            userStatus.setStatus(404);
            userStatus.setMessage(bindingResult.getFieldError().getDefaultMessage());
            return userStatus;
        }
        User userFind = userService.updateUser(user);
        userStatus.setStatus(200);
        userStatus.setMessage("成功");
        Map<String, Object> map = new HashMap<>();
        map.put("user", userFind);
        userStatus.setData(map);
        return userStatus;
    }

    /**
     * 修改用户余额
     *
     * @param id
     * @param deposit
     *
     */
    @PostMapping(value = "/updateDeposit")
    public Status updateDeposit(@RequestBody User user){
        Status status = new Status();
        User userFind = userService.updateDeposit(user.getId(), user.getDeposit());
        status.setStatus(200);
        status.setMessage("成功");
        Map<String, Object> map = new HashMap<>();
        map.put("user", userFind);
        status.setData(map);
        return status;
    }

    /**
     * 修改支付密码
     *
     * @param id
     * @param payment
     *
     */
    @PostMapping(value = "updatePayment")
    public Status updatePayment(@RequestBody User user){
        Status status = new Status();
        User userFind = userService.updatePayment(user.getId(), user.getPayment());
        status.setStatus(200);
        status.setMessage("成功");
        Map<String, Object> map = new HashMap<>();
        map.put("user", userFind);
        status.setData(map);
        return status;
    }
}
