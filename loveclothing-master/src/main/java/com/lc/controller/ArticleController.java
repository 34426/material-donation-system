package com.lc.controller;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.lc.entity.Article;
import com.lc.entity.User;
import com.lc.service.ArticleService;
import com.lc.utils.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * 文章信息控制层
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    ArticleService articleService;


    /**
     * 文章信息数据表格接口
     */
    @RequestMapping(value = "/getTableData", produces = "application/json; charset=utf-8")
    public String getTableData(@RequestBody Article article) {
        Map<String, Object> pageDataMap = new HashMap<>(3);
        //默认分页参数
        if(article.getCurrentPage() == null || article.getLimitCount() == null){
            article.setCurrentPage(1);
            article.setLimitCount(10);
        }
        List<Article> dataList = articleService.selectList(article);
        for(Article a : dataList){
            if(!StrUtil.isBlank(a.getPicStr())){
                a.setCoverImg(a.getPicStr().split(",")[0]);
            }
        }
        Integer totalCount = articleService.selectCount(article);
        pageDataMap.put("code", 0);
        pageDataMap.put("data", dataList);
        pageDataMap.put("count", totalCount);
        return JSON.toJSONString(pageDataMap);
    }

    /**
     * 文章信息保存
     */
    @RequestMapping("/saveArticle")
    public String saveArticle(@RequestBody Article article) {
        return articleService.saveArticle(article);
    }

    /**
     * 文章信息删除(物理删除)
     */
    @RequestMapping("/deleteArticle")
    public String deleteArticle(String id) {
        return articleService.deletePhysical(id);
    }

    /**
     * 我的文章数据获取
     */
    @RequestMapping("/selfArticle")
    public List<Article> selfArticle() {
        User currentUser = UserContext.getCurrentUser();
        List<Article> articleList = articleService.selectByUserId(currentUser.getId());
        return articleList;
    }

    /**
     * 根据id获取
     */
    @RequestMapping("/getById")
    public Article getById(String id) {
        Article article = articleService.selectEntity(id);
        if(!StrUtil.isBlank(article.getPicStr())){
            List<String> picList = new ArrayList<>(Arrays.asList(article.getPicStr().split(",")));
            article.setPicList(picList);
        }
        return article;
    }


}
