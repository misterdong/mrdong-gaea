package com.mrdong.gaea.gaeauser.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: liudong
 * @date: 2019-06-13 18:14
 **/
@Data
public class User implements Serializable {
    private static final long serialVersionUID = -8075712936726016771L;

    private Integer uid;

    private String name;

    private String phone;

    private String password;

    private String token;
}
