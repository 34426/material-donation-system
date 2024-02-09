package com.lc.utils;

import com.lc.entity.User;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

/**
 *	RequestHand工具类，用于存取登录用户session
 */
public class UserContext {

    private static final String USER_IN_SESSION = "CURRENT_USER";


    public static HttpSession getSession() {
        return ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest().getSession();
    }


    /**
     * 当前用户存入session
     * @param user 当前登录用户对象
     */
    public static void setCurrentUser(User user) {
        if(user == null) {
            getSession().invalidate();
        }else {
            getSession().setAttribute(USER_IN_SESSION,user);
        }
    }


    /**
     * 当前登录用户取出
     * @return 返回当前登录对象实体
     */
    public static User getCurrentUser() {
        return (User) getSession().getAttribute(USER_IN_SESSION);
    }

}
