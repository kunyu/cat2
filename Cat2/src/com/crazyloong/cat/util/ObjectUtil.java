package com.crazyloong.cat.util;

import java.util.List;
import java.util.Map;

public class ObjectUtil {

    private static final String SPACE = " ";
    private static final String ZHE = "折";
    private static final String JIAN = "件";

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

    /**
    * 功能描述：获取最低折扣
    * @Param:
     * @param activityLabels
    * @Return: java.lang.Double
    * @Author:
    * @Date: 2022/4/10 20:45
    * @Description:
    */
    public static Double getLowestDiscount(List<String> activityLabels){
        Double lowestDiscount = 10.0;
        for (int i = 0; i < activityLabels.size(); i++) {
            String[] labels = activityLabels.get(i).split(SPACE);
            for (int j = 0; j < labels.length; j++) {
                if (labels[j].contains(ZHE)) {
                    int index = labels[j].indexOf(ZHE);
                    labels[j] = labels[j].substring(0,index);
                }
                if (labels[j].contains(JIAN)) {
                    int index = labels[j].indexOf(JIAN);
                    labels[j] = labels[j].substring(index+1);
                }
                Double discount = Double.valueOf(labels[j]);
                if (discount < lowestDiscount) {
                    lowestDiscount = discount;
                }
            }
        }
        return lowestDiscount;
    }

}
