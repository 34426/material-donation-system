package com.lc.filter;

import com.lc.entity.User;
import com.lc.utils.UserContext;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {

    /**
     * 在请求处理之前进行调用（Controller方法调用之前）
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        try {
            User user = UserContext.getCurrentUser();
            if (user != null) {
                return true;
            }
            response.sendRedirect(request.getContextPath() + "/page/loginPage");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}
