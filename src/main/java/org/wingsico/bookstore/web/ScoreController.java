package org.wingsico.bookstore.web;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.wingsico.bookstore.common.JsonResult;
import org.wingsico.bookstore.service.ScoreService;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping(value = "/score")
public class ScoreController {
    @Resource
    private ScoreService scoreService;

    private JsonResult jsonResult = new JsonResult();

    /**
     * 获取评分
     * @return
     */
    @GetMapping(value = "/getScore")
    public Object getScore(@RequestParam("bookId") int bookId){
        return jsonResult.success(scoreService.getScore(bookId));
    }

    /**
     * 进行评分
     * @param body
     * @return
     */
    @PostMapping(value = "/setScore")
    public Object setScore(@RequestHeader("Authorization") int userId, @RequestBody Map<String, Object> body){
        if (!(body.containsKey("bookId")&&body.containsKey("point"))){
            jsonResult.failed(400, "缺少参数", HttpStatus.BAD_REQUEST);
        }
        int bookId = (Integer) body.get("bookId");
        double point = (Double) body.get("point");
        if (scoreService.isScore(bookId, userId)){
            return jsonResult.failed(400, "您已经对该本书进行评分", HttpStatus.BAD_REQUEST);
        }
        scoreService.setScore(bookId, userId, point);
        return jsonResult.success();
    }
}
