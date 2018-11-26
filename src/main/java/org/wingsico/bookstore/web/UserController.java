package org.wingsico.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.wingsico.bookstore.domain.User;
import org.wingsico.bookstore.service.UserService;

import java.util.List;

/**
 * User控制层
 *
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    UserService userService;

    /**
     * 获取 User 列表
     *
     */
    @GetMapping(value = {"", "/"})
    public List<User> getUserList(){
        return userService.findall();
    }

    /**
     * 进行增加用户
     *
     */
    @GetMapping(value = "/insert")
    public String insertUser(@ModelAttribute("user") String username, @ModelAttribute("password") String password){
        User user = new User();
        user.setUser(username);
        user.setPassword(password);
        userService.insertUser(user);
        return "加入成功";
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
        user.setUser(username);
        user.setPassword(password);
        userService.updataUser(user);
        return "更新成功";
    }
}
