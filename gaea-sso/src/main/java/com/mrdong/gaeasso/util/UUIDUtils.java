package com.mrdong.gaeasso.util;

import java.util.UUID;

/**
 * @description:
 * @author: liudong
 * @date: 2019-06-21 10:19
 **/
public class UUIDUtils {

    private UUIDUtils(){}

    public static String getUUID(){
        UUID uuid = UUID.randomUUID();

       return uuid.toString().replace("-","");
    }
}
