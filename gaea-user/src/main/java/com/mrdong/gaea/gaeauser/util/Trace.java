package com.mrdong.gaea.gaeauser.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: trace
 * @author: liudong
 * @date: 2019-04-26 15:42
 **/
public class Trace {

    private static Trace instance = new Trace();
    private Trace(){}

    private  ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    public static Trace getInstance() {
        return instance;
    }

    private int value;

    public int getValue() {
        return  this.threadLocal.get();
    }

    public void setValue(int value) {
        HashMap map = new HashMap();
        map.put("value",value);
        this.threadLocal.set(value);
    }
}
