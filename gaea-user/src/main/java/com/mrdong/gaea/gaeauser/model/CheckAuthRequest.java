package com.mrdong.gaea.gaeauser.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: liudong
 * @date: 2019-06-13 17:43
 **/
@Data
public class CheckAuthRequest implements Serializable {
    private static final long serialVersionUID = -3098904666323384319L;

    private Integer uid;

}
