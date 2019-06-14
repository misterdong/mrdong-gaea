package com.mrdong.gaeasso.controller;

/**
 * @description:
 * @author: liudong
 * @date: 2019-06-14 14:57
 **/
public class BaseController {

    ThreadLocal<String> uidLocal = new ThreadLocal<>();

     public void setUid(String uid){
         uidLocal.set(uid);
     }
     public String getUid(){
         return uidLocal.get();
     }
}
