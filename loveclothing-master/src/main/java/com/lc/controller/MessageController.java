package com.lc.controller;

import com.alibaba.fastjson.JSON;
import com.lc.entity.Article;
import com.lc.entity.Message;
import com.lc.service.MessageService;
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
@RequestMapping("/message")
public class MessageController {

    @Autowired
    MessageService messageService;


    /**
     * 留言信息数据表格接口
     */
    @RequestMapping(value = "/getTableData", produces = "application/json; charset=utf-8")
    public String getTableData(@RequestBody Message message) {
        Map<String, Object> map = messageService.selectPage(message);
        return JSON.toJSONString(map);
    }

    /**
     * 留言信息保存
     */
    @RequestMapping("/saveMessage")
    public String saveMessage(@RequestBody Message message) {
        return messageService.saveMessage(message);
    }

    /**
     * 留言信息删除(物理删除)
     */
    @RequestMapping("/deleteMessage")
    public String deleteMessage(String id) {
        return messageService.deletePhysical(id);
    }

    /**
     * 根据文章id获取留言
     */
    @RequestMapping("/getByArticleId")
    public List<Message> getByArticleId(String articleId) {
        List<Message> messageList = messageService.selectByArticleId(articleId);
        return messageList;
    }


}
