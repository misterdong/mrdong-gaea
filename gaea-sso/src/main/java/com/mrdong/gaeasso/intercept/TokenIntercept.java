package com.mrdong.gaeasso.intercept;

import com.mrdong.gaeasso.annotation.Anonymous;
import com.mrdong.gaeasso.controller.BaseController;
import com.mrdong.gaeasso.service.ISSOService;
import com.mrdong.gaeasso.util.CookieUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @description:
 * @author: liudong
 * @date: 2019-06-13 18:29
 **/
@Component
public class TokenIntercept extends HandlerInterceptorAdapter {

    private final String ACCESS_TOKEN = "access_token";

    @Autowired
    private ISSOService issoService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Object bean = handlerMethod.getBean();

        if (isAnonymous(handlerMethod)){
            return true;
        }
        String token = CookieUtils.getCookie(request, ACCESS_TOKEN);
        if (StringUtils.isEmpty(token)){
            response.getWriter().write("{\"code\":\"403\",\"msg\":\"no login\"}");
            return false;
        }

        //TODO 校验token有效性
        String uid = issoService.checkToken(token,response);
        if (StringUtils.isNotEmpty(uid)){
            BaseController baseController = (BaseController)bean;
            baseController.setUid(uid);
        }

        return super.preHandle(request, response, handler);
    }

    private boolean isAnonymous(HandlerMethod handlerMethod){

        Object bean = handlerMethod.getBean();
        Class clazz = bean.getClass();

        if (clazz.getAnnotation(Anonymous.class)!=null){
            return true;
        }
        Method method = handlerMethod.getMethod();

        return method.getAnnotation(Anonymous.class)!=null;
    }
}
