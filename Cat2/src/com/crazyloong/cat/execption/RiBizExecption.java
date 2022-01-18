package com.crazyloong.cat.execption;

/**
 * @author YPLI
 * @description
 * @date 2022/1/14 0014 21:24
 **/
public class RiBizExecption extends RuntimeException {
    //定义无参构造方法
    public RiBizExecption() {
        super();
    }

    public RiBizExecption(String message) {
        super(message);
    }

    //定义有参构造方法
    public RiBizExecption(String message,Throwable cause) {
        super(message,cause);
    }
}
