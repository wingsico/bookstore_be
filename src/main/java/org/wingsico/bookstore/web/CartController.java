package org.wingsico.bookstore.web;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wingsico.bookstore.domain.Order;
import org.wingsico.bookstore.service.CommodityService;
import org.wingsico.bookstore.service.OrderService;
import org.wingsico.bookstore.status.Status;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Cart控制层
 *
 */
@RestController
@RequestMapping(value = "/cart")
public class CartController {

    @Autowired
    OrderService orderService;

    /**
     * 获取用户的全部订单
     *
     */
    @PostMapping(value = "/allOrders")
    public Status findUserOrders(@RequestBody Order order){
        Status status = new Status();
        status.setStatus(200);
        status.setMessage("成功");
        Map<String, Object> map = new HashMap<>();
        try {
            List<Order> orders = orderService.findUserOrders(order.getUserID());
            map.put("orders", orders);
        }catch (NullPointerException ex){}
        status.setData(map);
        return status;
    }

    /**
     * 新建订单
     *
     */
    @PostMapping(value = "/create")
    public Status createOrder(@RequestBody Order order){
        Status status = new Status();
        status.setStatus(200);
        status.setMessage("成功");
        Map<String, Object> map = new HashMap<>();
        Order addedOrder = orderService.addOrder(order.getUserID(), order.getBookID());
        map.put("order", addedOrder);
        status.setData(map);
        return status;
    }

    /**
     * 删除订单
     *
     */
    @PostMapping(value = "/delete")
    public Status deleteOrder(@RequestBody Order order){
        Status status = new Status();
        status.setStatus(200);
        status.setMessage("成功");
        orderService.deleteOrder(order.getOrderID());
        return status;
    }

    /**
     * 修改订单的状态
     *
     */
    @PostMapping(value = "/update")
    public Status updateStatus(@RequestBody Order order){
        Status status = new Status();
        status.setStatus(200);
        status.setMessage("成功");
        Map<String, Object> map = new HashMap<>();
        Order updatedOrder = orderService.modifyStatus(order.getOrderID());
        map.put("order", updatedOrder);
        status.setData(map);
        return status;
    }
}
