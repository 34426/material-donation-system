package com.lc.dao;

import com.lc.base.BaseCrudDao;
import com.lc.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserDao extends BaseCrudDao<User> {

    /**
     * 根据登录名查询
     * @param loginName 用户登录账号
     */
    public User selectByLoginName(@Param("loginName")String loginName);

    /**
     * 根据登录名和密码查询
     * @param user 只带loginName（登录账号）和userPassword（登录密码）
     */
    public User selectLogin(User user);

}
