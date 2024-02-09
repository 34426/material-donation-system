package com.lc.controller;

import com.alibaba.fastjson.JSON;
import com.lc.entity.Wish;
import com.lc.service.WishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 留言信息控制层
 */
@RestController
@RequestMapping("/wish")
public class WishController {

    @Autowired
    WishService wishService;


    /**
     * 心愿信息数据表格接口
     */
    @RequestMapping(value = "/getTableData", produces = "application/json; charset=utf-8")
    public String getTableData(@RequestBody Wish wish) {
        Map<String, Object> map = wishService.selectPage(wish);
        return JSON.toJSONString(map);
    }

    /**
     * 心愿信息保存
     */
    @RequestMapping("/saveWish")
    public String saveWish(@RequestBody Wish wish) {
        return wishService.saveWish(wish);
    }

    /**
     * 心愿信息删除(物理删除)
     */
    @RequestMapping("/deleteWish")
    public String deleteWish(String id) {
        return wishService.deletePhysical(id);
    }

    /**
     * 根据用户id获取心愿
     */
    @RequestMapping("/getByUserId")
    public List<Wish> getByUserId(String userId) {
        List<Wish> messageList = wishService.selectByUserId(userId);
        return messageList;
    }


}
