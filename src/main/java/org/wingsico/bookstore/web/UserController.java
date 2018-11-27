package org.wingsico.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.wingsico.bookstore.data.UserData;
import org.wingsico.bookstore.domain.User;
import org.wingsico.bookstore.service.UserService;
import org.wingsico.bookstore.status.UserStatus;

import javax.validation.Valid;
import java.util.List;

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
     * 进行增加用户
     *
     */
    @PostMapping(value = "/register")
    public UserStatus registerUser(@Valid @RequestBody User user, BindingResult bindingResult){
        UserStatus userStatus = new UserStatus();
        if (bindingResult.hasErrors()){
            userStatus.setStatus(404);
            userStatus.setMessage(bindingResult.getFieldError().getDefaultMessage());
            return userStatus;
        }
        userService.insertUser(user);
        userStatus.setStatus(200);
        userStatus.setMessage("成功");
        UserData data = new UserData();
        data.setUser(user);
        userStatus.setData(data);
        return userStatus;
    }

    /**
     * 进行删除用户
     * @param id
     *
     */
    @GetMapping(value = "/delete/{id}")
    public String deleteUser(@PathVariable("id") int id){
        User user = new User();
        user.setId(id);
        userService.deleteUser(user);
        return "删除成功";
    }

    /**
     * 进行更新用户
     * @param id
     */
    @GetMapping(value = "/update{id}")
    public String updateUser(@PathVariable("id") int id, @ModelAttribute("user") String username, @ModelAttribute("password") String password) {
        User user = new User();
        user.setId(id);
        user.setUserName(username);
        user.setPassword(password);
        userService.updataUser(user);
        return "更新成功";
    }
}
