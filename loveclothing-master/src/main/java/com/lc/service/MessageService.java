package com.lc.service;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.lc.base.BaseCrudService;
import com.lc.dao.MessageDao;
import com.lc.entity.Message;
import com.lc.entity.User;
import com.lc.utils.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MessageService extends BaseCrudService<MessageDao, Message> {

    @Autowired
    MessageDao messageDao;

    /**
     * 根据文章id获取
     */
    public List<Message> selectByArticleId(String articleId){
        return messageDao.selectByArticleId(articleId);
    }

    /**
     * 重写保存
     */
    @Transactional(readOnly = false)
    public String saveMessage(Message entity){
        if(StrUtil.isBlank(entity.getUserId())){
            User currentUser = UserContext.getCurrentUser();
            entity.setUserId(currentUser.getId());
        }
        if(entity.isNewRecord()){
            //新增
            entity.preInsert();
            messageDao.insert(entity);
        }else{
            //修改
            entity.preUpdate();
            messageDao.update(entity);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("msg", "success");
        return JSON.toJSONString(map);
    }

    /**
     * 根据用户id查询
     */
    public List<Message> selectByUserId(String userId){
        return messageDao.selectByUserId(userId);
    }

}
