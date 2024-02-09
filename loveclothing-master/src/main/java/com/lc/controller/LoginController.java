package com.lc.controller;

import com.alibaba.fastjson.JSON;
import com.lc.entity.User;
import com.lc.service.UserService;
import com.lc.utils.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    UserService userService;

    /**
     * 用户登录
     * @param user 带loginName账号和userPassword密码
     */
    @RequestMapping("/userLogin")
    public String userLogin(@RequestBody User user){
        try{
            return userService.loginUser(user);
        }catch (Exception e){
            e.printStackTrace();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("msg", "fail");
            return JSON.toJSONString(map);
        }
    }

    /**
     * 用户注册
     */
    @RequestMapping("/userRegister")
    public String userRegister(@RequestBody User user){
        try{
            return userService.saveUser(user);
        }catch (Exception e){
            e.printStackTrace();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("msg", "fail");
            return JSON.toJSONString(map);
        }
    }

}
