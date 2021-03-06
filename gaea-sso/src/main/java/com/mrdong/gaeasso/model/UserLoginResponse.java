package com.mrdong.gaeasso.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: liudong
 * @date: 2019-06-13 17:04
 **/
@Data
public class UserLoginResponse implements Serializable {

    private static final long serialVersionUID = -6957859025271107163L;

    private Integer uid;

    private String userName;

    private String token;

}
