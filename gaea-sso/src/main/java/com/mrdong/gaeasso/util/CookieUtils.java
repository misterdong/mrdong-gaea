package com.mrdong.gaeasso.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description:
 * @author: liudong
 * @date: 2019-06-13 18:57
 **/
public class CookieUtils {

    /**
     * 获取cookie值
     *
     * @param request
     * @return
     */
    public static String getCookie(HttpServletRequest request, String cookieName) {
        Cookie cookies[] = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(cookieName)) {
                return cookie.getValue();
            }
        }
        return null;
    }

    /**
     * 设置cookie
     * @param response
     * @param name
     * @param value
     * @param expire
     */
    public static void setCookie(HttpServletResponse response,String name,String value,int expire){
        Cookie cookie = new Cookie(name,value);
        cookie.setMaxAge(expire);
        response.addCookie(cookie);
    }
}
