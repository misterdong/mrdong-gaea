package com.mrdong.gaea.gaeauser.config;

import com.alibaba.druid.support.http.StatViewServlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

/**
 * @description:
 * @author: liudong
 * @date: 2019-06-14 16:15
 **/

@WebServlet(urlPatterns = "/druid/*",
        initParams = {
                @WebInitParam(name = "allow", value = "127.0.0.1"),
                @WebInitParam(name = "loginUsername", value = "admin"),
                @WebInitParam(name = "loginPassword", value = "123456"),
                @WebInitParam(name = "resetEnable", value = "false")
        })
public class DruidStatViewServlet extends StatViewServlet {
    private static final long serialVersionUID = 4332642081230141724L;

}
