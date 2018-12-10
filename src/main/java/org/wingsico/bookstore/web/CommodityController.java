package org.wingsico.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wingsico.bookstore.domain.Commodity;
import org.wingsico.bookstore.service.CommodityService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Commodity控制层
 *
 */
@RestController
@RequestMapping(value = "/commodity")
public class CommodityController {

    @Autowired
    CommodityService commodityService;

    /**
     * 获取该购物车中的全部商品
     *
     * @param userID
     *
     */
    @PostMapping(value = "allCommodities")
    public ResponseEntity<Map<String,Object>> getOrderCommodities(@RequestHeader("Authorization") int userID){
        Map<String, Object> map = new HashMap<>();
        map.put("status", 200);
        map.put("message", "成功");
        Map<String, Object> newMap = new HashMap<>();
        try {
            List<Commodity> commodities = commodityService.findOrderCommodities(userID);
            newMap.put("commodities", commodities);
        }catch (NullPointerException ex){}
        map.put("data", newMap);
        return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
    }

    /**
     * 购物车中增加增加商品种类
     *
     * @param userID
     * @param bookID
     * @param number
     *
     */
    @PostMapping(value = "addCommodities")
    public ResponseEntity<Map<String,Object>> addCommodities(@RequestHeader("Authorization") int userID, @RequestBody Commodity commodity){
        Map<String, Object> map = new HashMap<>();
        map.put("status", 200);
        map.put("message", "成功");
        Map<String, Object> newMap = new HashMap<>();
        Commodity finishedCommodity = commodityService.addCommodity(userID, commodity.getBookID(), commodity.getNumber());
        newMap.put("commodity", finishedCommodity);
        map.put("data", newMap);
        return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
    }

    /**
     * 购物车中减少商品
     *
     * @param userID
     * @param bookID
     *
     */
    @PostMapping(value = "deleteCommodities")
    public ResponseEntity<Map<String,Object>> deleteCommodities(@RequestHeader("Authorization") int userID, @RequestBody Commodity commodity){
        Map<String, Object> map = new HashMap<>();
        map.put("status", 200);
        map.put("message", "成功");
        commodityService.deleteCommodity(userID, commodity.getBookID());
        return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
    }

    /**
     * 修改商品的数目
     *
     * @param userID
     * @param bookID
     * @param number
     *
     */
    @PostMapping(value = "updateNumber")
    public ResponseEntity<Map<String,Object>> updateNumber(@RequestHeader("Authorization") int userID, @RequestBody Commodity commodity){
        Map<String, Object> map = new HashMap<>();
        map.put("status", 200);
        map.put("message", "成功");
        Map<String, Object> newMap = new HashMap<>();
        Commodity updatedCommodities = commodityService.modifyNumber(userID, commodity.getBookID(), commodity.getNumber());
        newMap.put("commodity", updatedCommodities);
        map.put("data", newMap);
        return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
    }
}
