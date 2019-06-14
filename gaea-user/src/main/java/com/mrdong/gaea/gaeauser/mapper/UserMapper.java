package com.mrdong.gaea.gaeauser.mapper;

import com.mrdong.gaea.gaeauser.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: liudong
 * @date: 2019-06-14 16:40
 **/
public interface UserMapper {

    String getPassword(@Param("userName") String userName);

    User getUserInfo(@Param("phone")String phone);
}
