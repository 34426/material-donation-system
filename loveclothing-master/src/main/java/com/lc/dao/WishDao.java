package com.lc.dao;

import com.lc.base.BaseCrudDao;
import com.lc.entity.Wish;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WishDao extends BaseCrudDao<Wish> {

    /**
     * 根据用户id删除
     */
    public void deleteByUserId(@Param("userId") String userId);

    /**
     * 根据用户id查询
     */
    public List<Wish> selectByUserId(@Param("userId") String userId);

}
