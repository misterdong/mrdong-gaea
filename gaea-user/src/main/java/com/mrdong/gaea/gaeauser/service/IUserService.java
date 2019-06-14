package com.mrdong.gaea.gaeauser.service;

import com.mrdong.gaea.gaeauser.model.CheckAuthRequest;
import com.mrdong.gaea.gaeauser.model.CheckAuthResponse;
import com.mrdong.gaea.gaeauser.util.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IUserService {

    /**
     * 登陆
     * @param name
     * @return
     */
    Result login(String phone);

    /**
     * 获取验证码
     * @param request
     * @param response
     */
    void getVerifyCode(HttpServletRequest request, HttpServletResponse response);

    Result checkToken(String token);
}
