package com.crazyloong.cat.util;

import java.util.Map;

public class ObjectUtil {

    /**
     * 是否具有参数
     */
    public static boolean isParametersExsit(Map parameters) {
        if(parameters==null){
            return false;
        }
        if (parameters.isEmpty()){
            return false;
        }
        return true;
    }
}
