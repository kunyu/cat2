package com.crazyloong.cat.rshainan.constant;

public interface RishangHNEnum {

    /**
    * HTTP GET登录方式
    */
    enum GetType{
        TOKEN,
        AUTHORIZATION,
        HN_ONCE_LOGIN,
        NONE,
        ;
    }

    /**
     * HTTP POST登录方式
     */
    enum PostType{
        TOKEN,
        AUTHORIZATION,
        HN_ONCE_LOGIN,
        RS_NEW,
        ;
    }
}
