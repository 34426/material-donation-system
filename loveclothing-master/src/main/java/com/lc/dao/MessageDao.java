package com.lc.dao;

import com.lc.base.BaseCrudDao;
import com.lc.entity.Message;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MessageDao extends BaseCrudDao<Message> {

    /**
     * 根据用户id删除
     */
    public void deleteByUserId(@Param("userId") String userId);

    /**
     * 根据文章id获取
     */
    public List<Message> selectByArticleId(@Param("articleId") String articleId);

    /**
     * 根据用户id查询
     */
    public List<Message> selectByUserId(@Param("userId") String userId);

}
