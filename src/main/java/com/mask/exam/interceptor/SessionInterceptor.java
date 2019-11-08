package com.mask.exam.interceptor;

import com.mask.exam.domain.TblUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//就是拦截器
@Component
    public class SessionInterceptor implements HandlerInterceptor {
    Logger log = LoggerFactory.getLogger(SessionInterceptor.class);
    /**
     * 进入拦截器后首先进入的方法
     * 返回false则不再继续执行
     * 返回true则继续执行
     */
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler)throws Exception
    {
        System.out.println("我是拦截器：我证明我进来了");
        HttpSession session=request.getSession();
        TblUser user = (TblUser) session.getAttribute("user");
        //String userName = user.getUserName();
        if(user==null)
        {
            System.out.println("我证明用户没有登录");

            response.sendRedirect(request.getContextPath()+"/toLogin");
            return false;
        }
        log.info("我证明"+user.getUserName()+"已经登录");
        return  true;
    }
    /**
     * 生成视图时执行，可以用来处理异常，并记录在日志中
     */
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object arg2, Exception exception){
        //-----------------//
    }

    /** -
     * 生成视图之前执行，可以修改ModelAndView
     */
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object arg2, ModelAndView arg3)
            throws Exception{
        //----------------------------//
    }

}

