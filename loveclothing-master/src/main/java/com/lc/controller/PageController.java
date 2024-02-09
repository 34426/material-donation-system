package com.lc.controller;

import com.lc.utils.UserContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面路由控制层
 */
@Controller
@RequestMapping("/page")
public class PageController {

    /**
     * 登录页面
     */
    @RequestMapping("loginPage")
    public String loginPage() {
        UserContext.setCurrentUser(null);
        return "loginPage";
    }

    /**
     * 注册页面
     */
    @RequestMapping("registerPage")
    public String registerPage() {
        return "registerPage";
    }

    /**
     * 前台首页页面
     */
    @RequestMapping("indexPage")
    public String indexPage() {
        return "user/indexPage";
    }

    /**
     * 前台捐赠页面
     */
    @RequestMapping("donationPage")
    public String donationPage() {
        return "user/donationPage";
    }

    /**
     * 前台论坛页面
     */
    @RequestMapping("forumPage")
    public String forumPage() {
        return "user/forumPage";
    }

    /**
     * 前台论坛发布页面
     */
    @RequestMapping("releaseForm")
    public String releaseForm() {
        return "user/releaseForm";
    }

    /**
     * 前台个人主页
     */
    @RequestMapping("personalPage")
    public String personalPage() {
        return "user/personalPage";
    }

    /**
     * 前台论坛详情页面
     */
    @RequestMapping("detailPage")
    public String detailPage(String articleId) {
        return "user/detailPage";
    }

    /**
     * 前台心愿页面
     */
    @RequestMapping("wishPage")
    public String wishPage() {
        return "user/wishPage";
    }

    /**
     * 前台心愿表单
     */
    @RequestMapping("wishForm")
    public String wishForm() {
        return "user/wishForm";
    }

    /**
     * 后台用户管理页面
     */
    @RequestMapping("managerUser")
    public String managerUser() {
        return "manager/managerUser";
    }

    /**
     * 后台用户表单
     */
    @RequestMapping("userForm")
    public String userForm() {
        return "manager/userForm";
    }

    /**
     * 后台捐赠记录管理页面
     */
    @RequestMapping("managerDonation")
    public String managerDonation() {
        return "manager/managerDonation";
    }

    /**
     * 后台论坛文章管理页面
     */
    @RequestMapping("managerForum")
    public String managerForum() {
        return "manager/managerForum";
    }

    /**
     * 后台留言管理页面
     */
    @RequestMapping("managerMessage")
    public String managerMessage(){
        return "manager/managerMessage";
    }

    /**
     * 后台心愿管理页面
     */
    @RequestMapping("managerWish")
    public String managerWish(){
        return "manager/managerWish";
    }





}
