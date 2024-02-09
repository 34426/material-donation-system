package com.lc.controller;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.lc.entity.*;
import com.lc.service.*;
import com.lc.utils.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 用户信息控制层
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    DonationService donationService;
    @Autowired
    ArticleService articleService;
    @Autowired
    MessageService messageService;
    @Autowired
    WishService wishService;


    /**
     * 用户信息数据表格接口
     */
    @RequestMapping(value="/getTableData", produces="application/json; charset=utf-8")
    public String getUserData(@RequestBody User user) {
        Map<String, Object> map = userService.selectPage(user);
        return JSON.toJSONString(map);
    }

    /**
     * 用户信息保存
     */
    @RequestMapping("/saveUser")
    public String saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    /**
     * 用户删除(物理删除)
     */
    @RequestMapping("/deleteUser")
    public String deleteUser(String id) {
        return userService.deletePhysical(id);
    }

    /**
     * 根据userId获取个人信息
     */
    @RequestMapping("getPersonalInformation")
    public Map<String, Object> getPersonalInformation(String userId){
        Map<String, Object> map = new HashMap<>();
        if(StrUtil.isBlank(userId)){
            User currentUser = UserContext.getCurrentUser();
            userId = currentUser.getId();
        }
        //用户个人信息
        User user = userService.selectEntity(userId);
        map.put("user", user);
        //用户捐赠总数量
        List<Donation> donationList = donationService.selectByUserId(userId);
        map.put("donationCount", donationList.stream().mapToDouble(Donation::getNumber).sum());
        //用户发帖数量
        List<Article> articleList = articleService.selectByUserId(userId);
        map.put("articleCount", articleList.size());
        //用户帖子
        map.put("article", articleList);
        //用户心愿数量
        List<Wish> wishList = wishService.selectByUserId(userId);
        map.put("wishCount", wishList.size());
        //用户心愿
        map.put("wish", wishList);
        //用户留言数量
        List<Message> messageList = messageService.selectByUserId(userId);
        map.put("messageCount", messageList.size());
        //各个捐赠类别的数量
        Map<Integer, List<Donation>> groupMap = donationList.stream().collect(Collectors.groupingBy(Donation::getKind));
        for(Map.Entry<Integer, List<Donation>> itemGroup : groupMap.entrySet()){
            map.put(String.valueOf(itemGroup.getKey()), itemGroup.getValue().stream().mapToDouble(Donation::getNumber).sum());
        }
        //补零
        for(int i=0; i<=5; i++){
            if(map.get(String.valueOf(i)) == null){
                map.put(String.valueOf(i), 0);
            }
        }
        return map;
    }


}
