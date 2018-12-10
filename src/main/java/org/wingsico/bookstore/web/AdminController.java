package org.wingsico.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wingsico.bookstore.domain.*;
import org.wingsico.bookstore.service.CommodityService;
import org.wingsico.bookstore.service.OrderCommodityService;
import org.wingsico.bookstore.service.OrderService;
import org.wingsico.bookstore.service.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Admin控制层
 *
 */
@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    OrderService orderService;
    @Autowired
    CommodityService commodityService;
    @Autowired
    UserService userService;
    @Autowired
    OrderCommodityService orderCommodityService;

    /**
     * 查看所有订单情况 或者分类后的订单情况
     *
     * @param 无 或者 status
     *
     */
    @PostMapping(value = "/allOrders")
    public ResponseEntity<Map<String,Object>> getUserOrders(Order order){
        Map<String, Object> map = new HashMap<>();
        map.put("status", 200);
        map.put("message", "成功");
        Map<String, Object> newMap = new HashMap<>();
        List<OrderShow> orderShows = new ArrayList<>();
        try {
            List<Order> orders = new ArrayList<>();
            if(order.getStatus() == 1 || order.getStatus() == 2){
                List<Order> allOrders = orderService.findAll();
                for(int i=0;i<allOrders.size();i++){
                    if(allOrders.get(i).getStatus() == order.getStatus()){
                        orders.add(allOrders.get(i));
                    }
                }
            }
            else {
                orders = orderService.findAll();
            }
            for(int i=0;i<orders.size();i++){
                OrderCommodity orderCommodity = orderCommodityService.query(orders.get(i).getUserID(), orders.get(i).getBookID());
                int j;
                for(j=0;j<orderShows.size();j++){
                    if(orderShows.get(j).getOrderID() == orders.get(i).getOrderID()){
                        orderShows.get(j).getOrderCommodities().add(orderCommodity);
                        break;
                    }
                }
                if(j>=orderShows.size()){
                    OrderShow orderShow = new OrderShow();
                    orderShow.setDate(orders.get(i).getDate());
                    orderShow.setStatus(orders.get(i).getStatus());
                    orderShow.setOrderID(orders.get(i).getOrderID());
                    orderShow.setUserID(orders.get(i).getUserID());
                    ArrayList<OrderCommodity> orderCommodities = new ArrayList<>();
                    orderCommodities.add(orderCommodity);
                    orderShow.setOrderCommodities(orderCommodities);
                    orderShows.add(orderShow);
                }
            }
        }catch (NullPointerException ex){}
        newMap.put("orders", orderShows);
        map.put("data", newMap);
        return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
    }

    /**
     * 增加书籍
     *
     */


    /**
     * 修改订单状态
     *
     * @param orderID
     * @param status
     *
     */
    @PostMapping(value = "/updateStatus")
    public ResponseEntity<Map<String,Object>> updateStatus(@RequestBody Order order){
        Map<String, Object> map = new HashMap<>();
        map.put("status", 200);
        map.put("message", "成功");
        Map<String, Object> newMap = new HashMap<>();
        OrderShow orderShow = new OrderShow();
        try {
            List<Order> orders = orderService.findAll();
            ArrayList<Integer> booksID = new ArrayList<>();
            for(int i=0;i<orders.size();i++){
                if (order.getOrderID() == orders.get(i).getOrderID()){
                    orderService.modifyStatus(order.getOrderID(), order.getStatus());
                    booksID.add(orders.get(i).getBookID());
                }
            }
            for(int i=0;i<orders.size();i++){
                if (order.getOrderID() == orders.get(i).getOrderID()){
                    Order orderFind = orders.get(i);
                    orderShow.setUserID(orderFind.getUserID());
                    orderShow.setOrderID(orderFind.getOrderID());
                    orderShow.setStatus(orderFind.getStatus());
                    orderShow.setDate(orderFind.getDate());
                }
            }
            ArrayList<OrderCommodity> orderCommodities = new ArrayList<>();
            for(int i=0;i<booksID.size();i++){
                OrderCommodity orderCommodity = orderCommodityService.query(orderShow.getUserID(), booksID.get(i));
                orderCommodities.add(orderCommodity);
            }
            orderShow.setOrderCommodities(orderCommodities);
        }catch (NullPointerException ex){}
        newMap.put("order", orderShow);
        map.put("data", newMap);
        return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
    }

    /**
     * 按照用户名来查询用户信息
     *
     * @param username
     *
     */
    @PostMapping(value = "/query")
    public ResponseEntity<Map<String,Object>> loginUser(@RequestBody User user){
        Map<String, Object> map = new HashMap<>();
        map.put("status", 200);
        map.put("message", "成功");
        try {
            List<User> users = userService.findAll();
            for (User userFind:users){
                if(userFind.getUsername().equals(user.getUsername())){
                    UserShow userShow = new UserShow();
                    userShow.setId(userFind.getId());
                    userShow.setUsername(userFind.getUsername());
                    userShow.setNickname(userFind.getNickname());
                    userShow.setDeposit(userFind.getDeposit());
                    userShow.setRole(userFind.getRole());
                    Map<String, Object> userMap = new HashMap<>();
                    userMap.put("user", userShow);
                    map.put("data", userMap);
                    return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
                }
            }
        }catch (NullPointerException ex){}
        return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
    }

    /**
     * 返回所有用户信息
     *
     */
    @GetMapping(value = "/allUsers")
    public ResponseEntity<Map<String,Object>> allUsers(){
        Map<String, Object> map = new HashMap<>();
        map.put("status", 200);
        map.put("message", "成功");
        try {
            List<User> users = userService.findAll();
            ArrayList<UserShow> userShows = new ArrayList<>();
            for (User userFind:users) {
                UserShow userShow = new UserShow();
                userShow.setId(userFind.getId());
                userShow.setUsername(userFind.getUsername());
                userShow.setNickname(userFind.getNickname());
                userShow.setDeposit(userFind.getDeposit());
                userShow.setRole(userFind.getRole());
                userShows.add(userShow);
            }
            Map<String, Object> userMap = new HashMap<>();
            userMap.put("users", userShows);
            map.put("data", userMap);
            return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
        }catch (NullPointerException ex){}
        return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
    }
}
