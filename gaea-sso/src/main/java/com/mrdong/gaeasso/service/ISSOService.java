package com.mrdong.gaeasso.service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ISSOService {

   String login(String phone, String password, HttpServletResponse response);

    String checkToken(String token,HttpServletResponse response) throws IOException;
}
