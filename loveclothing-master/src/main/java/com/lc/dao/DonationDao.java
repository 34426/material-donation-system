package com.lc.dao;

import com.lc.base.BaseCrudDao;
import com.lc.entity.Donation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DonationDao extends BaseCrudDao<Donation> {

    /**
     * 根据用户id删除
     */
    public void deleteByUserId(@Param("userId") String userId);

    /**
     * 根据List新增插入
     */
    public void insertList(@Param("list") List<Donation> list);

    /**
     * 根据用户id查询捐赠记录，按时间降序
     */
    public List<Donation> selectByUserId(@Param("userId") String userId);

    /**
     * 根据id修改状态
     */
    public void updateVerifyById(@Param("id") String id, @Param("verify")Integer verify);

}
