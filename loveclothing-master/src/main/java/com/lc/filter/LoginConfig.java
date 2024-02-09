package com.lc.filter;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 登录拦截器配置类
 */
@Configuration
public class LoginConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(new LoginInterceptor());
        //所有路径都被拦截
        registration.addPathPatterns("/**");
        //添加不拦截路径
        registration.excludePathPatterns(
                //登录释放
                "/page/loginPage",
                "/page/registerPage",
                "/login/userLogin",
                "/login/userRegister",
                //静态资源
                "/**/*.html",
                "/**/*.js",
                "/**/*.css",
                "/**/*.jpg",
                "/**/*.png",
                "/**/*.eot",
                "/**/*.ttf",
                "/**/*.woff",
                "/**/*.woff2",
                "/**/*.svg",
                "/**/*.gif"
        );
    }

}
