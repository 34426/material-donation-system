package com.lc.service;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.lc.base.BaseCrudService;
import com.lc.controller.FileController;
import com.lc.dao.ArticleDao;
import com.lc.entity.Article;
import com.lc.entity.User;
import com.lc.utils.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ArticleService extends BaseCrudService<ArticleDao, Article> {

    @Autowired
    ArticleDao articleDao;

    /**
     * 根据用户id查询
     */
    @Transactional(readOnly = true)
    public List<Article> selectByUserId(String userId){
        return articleDao.selectByUserId(userId);
    }

    /**
     * 重写保存
     */
    @Transactional(readOnly = false)
    public String saveArticle(Article entity){
        if(StrUtil.isBlank(entity.getUserId())){
            User currentUser = UserContext.getCurrentUser();
            entity.setUserId(currentUser.getId());
        }
        if(entity.isNewRecord()){
            //新增
            entity.preInsert();
            articleDao.insert(entity);
        }else{
            //修改
            entity.preUpdate();
            articleDao.update(entity);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("msg", "success");
        return JSON.toJSONString(map);
    }

    /**
     * 重写物理删除(删除数据库记录)
     * @param id 记录id
     */
    @Transactional(readOnly = false)
    public String deletePhysical(String id){
        //删除图片
        Article article = articleDao.selectEntity(id);
        String picStr = article.getPicStr();
        if(!StrUtil.isBlank(picStr)){
            String[] picArr = picStr.split(",");
            for(String pic : picArr){
                File file = new File(FileController.filePrePath + "forumImg\\" + pic);
                file.delete();
            }
        }
        //删除用户文章
        articleDao.deletePhysical(id);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("msg", "success");
        return JSON.toJSONString(map);
    }
    

}
