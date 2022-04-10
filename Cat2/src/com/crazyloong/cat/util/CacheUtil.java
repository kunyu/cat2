package com.crazyloong.cat.util;

import com.crazyloong.cat.rishang.mybatis.entity.RiOrderPhone;
import com.crazyloong.cat.rishang.mybatis.service.RiOrderPhoneService;
import com.crazyloong.cat.rshainan.dto.HNReq;
import com.crazyloong.cat.rshainan.dto.TokenEntity;
import com.crazyloong.cat.rshainan.service.RSHaiNanService;
import com.mysql.jdbc.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Author : crazyloongcat
 * @Date :2022/3/17 21:53
 * @Description : 缓存管理工具
 */
@Component
public class CacheUtil {
    @Autowired
    private RSHaiNanService rsHaiNanService;

    @Autowired
    private RiOrderPhoneService riOrderPhoneService;

    @Autowired
    private CacheManager cacheManager;

    public <T> T get(String key,Class<T> tClass){
        return cacheManager.getCache("user_cache").get(key,tClass);
    }

    public <T> void put(String key,T t){
        cacheManager.getCache("user_cache").put(key,t);
    }


    /**
    * 功能描述： 缓存中获取token 校验token是否过期  过期重新登录
    * @Param:
     * @param phone
    * @Return: java.lang.String
    * @Author:
    * @Date: 2022/3/21 20:41
    * @Description:
    */
    public synchronized String getHNToken(String phone){

        RiOrderPhone orderPhone = riOrderPhoneService.getByPhone(phone);
        Map<String, TokenEntity> hnToken =  this.get("HNToken", Map.class);
        if (hnToken == null ) {
            hnToken = new HashMap<>();
            String  phoneToken = this.hnReLogin(phone);
            hnToken.put(orderPhone.getPhone(),new TokenEntity(phoneToken));
            this.put("HNToken",hnToken);
            return phoneToken;
        } else {
            AtomicReference<String> phoneToken = new AtomicReference<>();
            hnToken.forEach((key,value)->{
                if (key.equals(orderPhone.getPhone()) ) {
                    if (System.currentTimeMillis() - value.getLastPutTime() < 30*60*1000) {
                        // 未过30分钟超时时间 返回缓存的值
                        phoneToken.set(value.getToken());
                    } else if (rsHaiNanService.checkToken(value.getToken())) {
                        // 缓存生效 返回缓存的值
                        phoneToken.set(value.getToken());
                    }
                }
            });
            if (StringUtils.isNullOrEmpty(phoneToken.get())) {
                phoneToken.set(this.hnReLogin(phone));
                hnToken.put(orderPhone.getPhone(),new TokenEntity(phoneToken.get()));
                this.put("HNToken",hnToken);
            }
            return phoneToken.get();
        }
    }

    public String hnReLogin(String phone){
        RiOrderPhone orderPhone = riOrderPhoneService.getByPhone(phone);
        HNReq hnReq = new HNReq();
        hnReq.setPhone(orderPhone.getPhone());
        hnReq.setPassword(orderPhone.getPassword());
        return rsHaiNanService.login(hnReq);
    }

}
