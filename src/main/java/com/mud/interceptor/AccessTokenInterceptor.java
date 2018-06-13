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
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Created by leeesven on 17/8/22.
 */

@Component
public class AccessTokenInterceptor implements HandlerInterceptor{

    private Logger logger = Logger.getLogger(AccessTokenInterceptor.class.getName());

    private ArrayList<String> ignoreUrlList = new ArrayList<>();

    public AccessTokenInterceptor() {
        ignoreUrlList.add("api/user/register");
        ignoreUrlList.add("api/user/login");
    }

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        logger.info("preHandle-------------");
        boolean dontNeedAuth = false;

        for (int i=0; i<ignoreUrlList.size(); i++){
            int index = httpServletRequest.getRequestURL().indexOf(ignoreUrlList.get(i));
            if (index > 0){
                dontNeedAuth = true;
            }
        }

        //dontNeedAuth = true;

        String token = httpServletRequest.getHeader(HttpMacro.SESSION_ID);
        UserAuth userAuth = userAuthDao.getUserAuthByAccessToken(token);

        if (userAuth != null || dontNeedAuth){
            UserContext context = new UserContext(userAuth);
            return true;
        }

        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        logger.info("postHandle-------------");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        logger.info("afterCompletion-------------");
    }

    @Autowired
    UserAuthDao userAuthDao;
}
