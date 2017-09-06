package com.mud.interceptor;

import com.mud.context.UserContext;
import com.mud.dao.UserAuthDao;
import com.mud.mapper.UserAuth;
import com.mud.model.define.HttpMacro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by leeesven on 17/8/22.
 */

@Component
public class AccessTokenInterceptor implements HandlerInterceptor{

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        System.out.println("preHandle-------------");

        String token = httpServletRequest.getHeader(HttpMacro.SESSION_ID);
        UserAuth userAuth = userAuthDao.getUserAuthByAccessToken(token);

        if (userAuth != null){
            UserContext context = new UserContext(userAuth);
            return true;
        }

        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle-------------");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("afterCompletion-------------");
    }

    @Autowired
    UserAuthDao userAuthDao;
}
