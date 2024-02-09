package com.lc.service;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.lc.base.BaseCrudService;
import com.lc.controller.FileController;
import com.lc.dao.*;
import com.lc.entity.User;
import com.lc.utils.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService extends BaseCrudService<UserDao, User> {

    @Autowired
    UserDao userDao;
    @Autowired
    DonationDao donationDao;
    @Autowired
    ArticleDao articleDao;
    @Autowired
    MessageDao messageDao;
    @Autowired
    WishDao wishDao;

    /**
     * 登录查询
     * @param user 只带loginName（登录账号）,userPassword（登录密码）
     */
    @Transactional(readOnly = false)
    public String loginUser(User user){
        Map<String, Object> map = new HashMap<String, Object>();
        User currentUser = userDao.selectLogin(user);
        if(currentUser == null){
            map.put("msg", "noUser");
        }else{
            //更新上次登录时间
            currentUser.setLastLoginDate(DateUtil.now());
            userDao.update(currentUser);
            //存入session
            UserContext.setCurrentUser(currentUser);
            map.put("msg", currentUser.getUserType());
        }
        return JSON.toJSONString(map);
    }

    /**
     * 重写保存用户，校验数据的唯一性
     */
    @Transactional(readOnly = false)
    public String saveUser(User user){
        Map<String, Object> map = new HashMap<String, Object>();
        User u = userDao.selectByLoginName(user.getLoginName());
        if(user.isNewRecord()){
            //新增
            if(u != null){
                //登录名重复
                map.put("msg", "loginNameRepeat");
                return JSON.toJSONString(map);
            }else{
                //新增的话，随机给一个默认头像
                user.setUserImg(FileController.randomGetDefaultUserImg());
                user.preInsert();
                userDao.insert(user);
            }
        }else{
            //修改
            if(u != null && !u.getId().equals(user.getId())){
                //登录名重复
                map.put("msg", "loginNameRepeat");
                return JSON.toJSONString(map);
            }else{
                user.preUpdate();
                userDao.update(user);
            }
        }
        map.put("msg", "success");
        return JSON.toJSONString(map);
    }

    /**
     * 重写物理删除(删除数据库记录)
     * @param id 记录id
     */
    @Transactional(readOnly = false)
    public String deletePhysical(String id){
        //删除用户心愿
        wishDao.deleteByUserId(id);
        //删除该用户的捐赠记录
        donationDao.deleteByUserId(id);
        //删除用户文章
        articleDao.deleteByUserId(id);
        //删除该用户的留言
        messageDao.deleteByUserId(id);
        //删除用户
        userDao.deletePhysical(id);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("msg", "success");
        return JSON.toJSONString(map);
    }


}
