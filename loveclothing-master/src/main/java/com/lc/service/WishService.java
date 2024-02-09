package com.lc.service;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.lc.base.BaseCrudService;
import com.lc.dao.WishDao;
import com.lc.entity.User;
import com.lc.entity.Wish;
import com.lc.utils.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WishService extends BaseCrudService<WishDao, Wish> {

    @Autowired
    WishDao wishDao;

    /**
     * 根据用户id查询
     */
    @Transactional(readOnly = true)
    public List<Wish> selectByUserId(String userId){
        return wishDao.selectByUserId(userId);
    }

    /**
     * 重写保存
     */
    @Transactional(readOnly = false)
    public String saveWish(Wish entity){
        if(StrUtil.isBlank(entity.getUserId())){
            User currentUser = UserContext.getCurrentUser();
            entity.setUserId(currentUser.getId());
        }
        if(entity.isNewRecord()){
            //新增
            entity.preInsert();
            wishDao.insert(entity);
        }else{
            //修改
            entity.preUpdate();
            wishDao.update(entity);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("msg", "success");
        return JSON.toJSONString(map);
    }

}
