package org.wingsico.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wingsico.bookstore.domain.Commodity;
import org.wingsico.bookstore.service.CommodityService;
import org.wingsico.bookstore.status.Status;

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
     * 获取订单的全部商品
     *
     * @param orderID
     *
     */
    @PostMapping(value = "allCommodities")
    public Status getOrderCommodities(@RequestBody Commodity commodity){
        Status status = new Status();
        status.setStatus(200);
        status.setMessage("成功");
        Map<String, Object> map = new HashMap<>();
        try {
            List<Commodity> commodities = commodityService.findOrderCommodities(commodity.getOrderID());
            map.put("commodities", commodities);
        }catch (NullPointerException ex){}
        status.setData(map);
        return status;
    }

    /**
     * 订单中增加商品种类
     *
     * @param orderID
     * @param bookID
     *
     */
    @PostMapping(value = "addCommodities")
    public Status addCommodities(@RequestBody Commodity commodity){
        Status status = new Status();
        status.setStatus(200);
        status.setMessage("成功");
        Map<String, Object> map = new HashMap<>();
        Commodity finishedCommodity = commodityService.addCommodity(commodity.getOrderID(), commodity.getBookID());
        map.put("commodity", finishedCommodity);
        status.setData(map);
        return status;
    }

    /**
     * 订单中减少商品
     *
     * @param orderID
     * @param bookID
     *
     */
    @PostMapping(value = "deleteCommodities")
    public Status deleteCommodities(@RequestBody Commodity commodity){
        Status status = new Status();
        status.setStatus(200);
        status.setMessage("成功");
        commodityService.deleteCommodity(commodity.getOrderID(), commodity.getBookID());
        return status;
    }

    /**
     * 修改商品的数目
     *
     * @param orderID
     * @param bookID
     * @param number
     *
     */
    @PostMapping(value = "updateNumber")
    public Status updateNumber(@RequestBody Commodity commodity){
        Status status = new Status();
        status.setStatus(200);
        status.setMessage("成功");
        Map<String, Object> map = new HashMap<>();
        Commodity updatedCommodities = commodityService.modifyNumber(commodity.getOrderID(), commodity.getBookID(), commodity.getNumber());
        map.put("commodity", updatedCommodities);
        status.setData(map);
        return status;
    }
}
