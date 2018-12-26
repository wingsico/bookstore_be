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
 * Cart控制层
 *
 */
@RestController
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    OrderService orderService;
    @Autowired
    CommodityService commodityService;
    @Autowired
    UserService userService;
    @Autowired
    OrderCommodityService orderCommodityService;

    /**
     * 获取用户的全部订单详情
     *
     * @param userID
     *
     */
    @GetMapping(value = "/allOrders")
    public ResponseEntity<Map<String,Object>> getUserOrders(@RequestHeader("Authorization") int userID){
        Map<String, Object> map = new HashMap<>();
        map.put("status", 200);
        map.put("message", "成功");
        Map<String, Object> newMap = new HashMap<>();
        List<OrderShow> orderShows = new ArrayList<>();
        try {
            List<Order> orders = orderService.findUserOrders(userID);
            for(int i=0;i<orders.size();i++){
                OrderCommodity orderCommodity = orderCommodityService.query(userID, orders.get(i).getBookID());
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
                    orderShow.setUserID(userID);
                    User user = userService.query(userID);
                    orderShow.setUsername(user.getUsername());
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
     * 获取单个订单的详情
     *
     * @param userID
     * @param orderID
     *
     */
    @PostMapping(value = "/findOrder")
    public ResponseEntity<Map<String,Object>> findOrder(@RequestHeader("Authorization") int userID, @RequestBody Order order){
        Map<String, Object> map = new HashMap<>();
        User userFind = userService.query(userID);
        Order order1 = orderService.query(order.getOrderID());
        if(order1.getOrderID()==0){
            map.put("status", 400);
            map.put("message", "没有该订单");
            return new ResponseEntity<Map<String,Object>>(map, HttpStatus.BAD_REQUEST);
        }
        if(userFind.getRole().equals("normal")&&order1.getUserID()!=userID){
            map.put("status", 403);
            map.put("message", "没有权限访问该订单");
            return new ResponseEntity<Map<String,Object>>(map, HttpStatus.FORBIDDEN);
        }
        map.put("status", 200);
        map.put("message", "成功");
        Map<String, Object> newMap = new HashMap<>();
        OrderShow orderShow = new OrderShow();
        try {
            List<Order> orders = orderService.findAll();
            ArrayList<Integer> booksID = new ArrayList<>();
            for(int i=0;i<orders.size();i++){
                if (order.getOrderID() == orders.get(i).getOrderID()){
                    booksID.add(orders.get(i).getBookID());
                }
            }
            for(int i=0;i<orders.size();i++){
                if (order.getOrderID() == orders.get(i).getOrderID()){
                    Order orderFind = orders.get(i);
                    orderShow.setUserID(orderFind.getUserID());
                    orderShow.setOrderID(orderFind.getOrderID());
                    orderShow.setStatus(orderFind.getStatus());
                    User user = userService.query(orderFind.getUserID());
                    orderShow.setUsername(user.getUsername());
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
     * 新建订单
     *
     * @param userID
     * @param bookIDs
     *
     */
    @PostMapping(value = "/create")
    public ResponseEntity<Map<String,Object>> createOrder(@RequestHeader("Authorization") int userID, @RequestBody OrderBooks orderBooks){
        Map<String, Object> map = new HashMap<>();
        map.put("status", 200);
        map.put("message", "成功");
        Map<String, Object> newMap = new HashMap<>();
        OrderBooks addedOrder = orderService.addOrder(userID, orderBooks.getBookIDs());
        OrderShow orderShow = new OrderShow();
        try {
            List<Order> orders = orderService.findAll();
            ArrayList<Integer> booksID = new ArrayList<>();
            for(int i=0;i<orders.size();i++){
                if (addedOrder.getOrderID() == orders.get(i).getOrderID()){
                    booksID.add(orders.get(i).getBookID());
                }
            }
            ArrayList<OrderCommodity> orderCommodities = new ArrayList<>();
            for(int i=0;i<booksID.size();i++){
                Commodity commodity = commodityService.query(userID, booksID.get(i));
                orderCommodityService.addCommodity(userID, commodity.getBookID());
            }
            for(int i=0;i<booksID.size();i++){
                OrderCommodity orderCommodity = orderCommodityService.query(userID, booksID.get(i));
                orderCommodities.add(orderCommodity);
            }
            for(int i=0;i<orders.size();i++){
                if (addedOrder.getOrderID() == orders.get(i).getOrderID()){
                    Order orderFind = orders.get(i);
                    orderShow.setUserID(orderFind.getUserID());
                    User user = userService.query(orderFind.getUserID());
                    orderShow.setUsername(user.getUsername());
                    orderShow.setOrderID(orderFind.getOrderID());
                    orderShow.setStatus(orderFind.getStatus());
                    orderShow.setDate(orderFind.getDate());
                }
            }
            orderShow.setOrderCommodities(orderCommodities);
        }catch (NullPointerException ex){}
        newMap.put("order", orderShow);
        map.put("data", newMap);
        return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
    }

    /**
     * 删除订单
     *
     * @param orderID
     *
     */
    @PostMapping(value = "/delete")
    public ResponseEntity<Map<String,Object>> deleteOrder(@RequestBody Order order){
        Map<String, Object> map = new HashMap<>();
        map.put("status", 200);
        map.put("message", "成功");
        orderService.deleteOrder(order.getOrderID());
        return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
    }

    /**
     * 支付订单
     *
     * @param userID
     * @param orderID
     * @param payment
     *
     */
    @PostMapping(value = "/pay")
    public ResponseEntity<Map<String,Object>> pay(@RequestHeader("Authorization") int userID, @RequestBody PayOrder payOrder){
        Map<String, Object> map = new HashMap<>();
        User user = userService.query(userID);
        try{
            if(!user.getPayment().equals(payOrder.getPayment())){
                map.put("status", 403);
                map.put("message", "支付密码不正确");
                return new ResponseEntity<Map<String,Object>>(map,HttpStatus.FORBIDDEN);
            }
            else {
                List<Order> orders = orderService.findUserOrders(userID);
                ArrayList<Integer> bookIDs = new ArrayList<>();
                for (int i = 0; i < orders.size(); i++) {
                    if (orders.get(i).getOrderID() == payOrder.getOrderID()) {
                        bookIDs.add(orders.get(i).getBookID());
                    }
                }
                for (int i = 0; i < bookIDs.size(); i++) {
                    Commodity commodity = commodityService.query(userID, bookIDs.get(i));
                    float deposit = user.getDeposit() - commodity.getNumber() * commodity.getPrice();
                    if(deposit < 0){
                        map.put("status", 400);
                        map.put("message", "余额不足");
                        return new ResponseEntity<Map<String,Object>>(map,HttpStatus.BAD_REQUEST);
                    }
                }
                for (int i = 0; i < orders.size(); i++) {
                    if (orders.get(i).getOrderID() == payOrder.getOrderID()) {
                        orderService.modifyStatus(payOrder.getOrderID(), 2);
                    }
                }
                for (int i = 0; i < bookIDs.size(); i++) {
                    Commodity commodity = commodityService.query(userID, bookIDs.get(i));
                    float deposit = user.getDeposit() - commodity.getNumber() * commodity.getPrice();
                    userService.updateDeposit(userID, deposit);
                    commodityService.deleteCommodity(userID, bookIDs.get(i));
                }
                map.put("status", 200);
                map.put("message", "成功");
            }
        }catch (NullPointerException ex){}
        return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
    }
}
