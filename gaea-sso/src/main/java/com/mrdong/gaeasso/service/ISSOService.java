package com.mrdong.gaeasso.service;

import com.mrdong.gaeasso.util.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ISSOService {

    Result login(String phone, String password, String verifyCode ,HttpServletRequest request,HttpServletResponse response);

    String checkToken(String token,HttpServletResponse response) throws IOException;

    /**
     * 获取验证码
     * @param request
     * @param response
     */
    void getVerifyCode(HttpServletRequest request, HttpServletResponse response);
}
