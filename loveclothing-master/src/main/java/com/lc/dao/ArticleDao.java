package com.lc.dao;

import com.lc.base.BaseCrudDao;
import com.lc.entity.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleDao extends BaseCrudDao<Article> {

    /**
     * 根据用户id删除
     */
    public void deleteByUserId(@Param("userId") String userId);

    /**
     * 根据用户id查询
     */
    public List<Article> selectByUserId(@Param("userId") String userId);

}
