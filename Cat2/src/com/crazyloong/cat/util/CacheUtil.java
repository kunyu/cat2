package com.crazyloong.cat.util;

import com.crazyloong.cat.execption.RiBizExecption;
import com.crazyloong.cat.rishang.mybatis.entity.RiOrderPhone;
import com.crazyloong.cat.rishang.mybatis.service.RiOrderPhoneService;
import com.crazyloong.cat.rishang.service.RiShangService;
import com.crazyloong.cat.rsNew.dto.RSNewUserInfo;
import com.crazyloong.cat.rsNew.dto.UserEntity;
import com.crazyloong.cat.rsNew.service.RSNewService;
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
    private RiShangService riShangService;
    @Autowired
    private RSNewService rsNewService;
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
        if (orderPhone == null) {
            throw new RiBizExecption("账号不存在");
        }
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

    /**
     * 功能描述： 缓存中获取token 校验token是否过期  过期重新登录
     * @Param:
     * @param phone
     * @Return: java.lang.String
     * @Author:
     * @Date: 2022/3/21 20:41
     * @Description:
     */
    public synchronized String getRiToken(String phone){
        Map<String, TokenEntity> riToken =  this.get("RiToken", Map.class);
        if (riToken == null ) {
            riToken = new HashMap<>();
            String password = getRiFirstToken();
            String token = riShangService.login(phone,password);
            riToken.put(phone,new TokenEntity(token));
            this.put("RiToken",riToken);
            return token;
        } else {
            AtomicReference<String> token = new AtomicReference<>();
            riToken.forEach((key,value)->{
                if (key.equals(phone)) {
                    if (System.currentTimeMillis() - value.getLastPutTime() < 30*60*1000) {
                        // 未过30分钟超时时间 返回缓存的值
                        token.set(value.getToken());
                    } else if (riShangService.checkToken(value.getToken())) {
                        // 缓存生效 返回缓存的值
                        token.set(value.getToken());
                    }
                }
            });
            if (StringUtils.isNullOrEmpty(token.get())) {
                String password = getRiFirstToken();
                String tok = riShangService.login(phone,password);
                token.set(tok);
                riToken.put(phone,new TokenEntity(token.get()));
                this.put("RiToken",riToken);
            }
            return token.get();
        }
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
    public synchronized RSNewUserInfo getRSNewToken(String phone){
        Map<String, UserEntity> rsNewToken =  this.get("RSNewToken", Map.class);
        if (rsNewToken == null ) {
            rsNewToken = new HashMap<>();
            RSNewUserInfo userInfo = rsNewService.login(phone);
            rsNewToken.put(phone,new UserEntity(userInfo));
            this.put("RSNewToken",rsNewToken);
            return userInfo;
        } else {
            AtomicReference<RSNewUserInfo> rsNewUserInfo = new AtomicReference<>();
            rsNewToken.forEach((key,value)->{
                if (key.equals(phone)) {
                    if (System.currentTimeMillis() - value.getLastPutTime() < 30*60*1000) {
                        // 未过30分钟超时时间 返回缓存的值
                        rsNewUserInfo.set(value.getUserInfo());
                    }
                }
            });
            if (rsNewUserInfo.get() == null ) {
                RSNewUserInfo user = rsNewService.login(phone);
                rsNewUserInfo.set(user);
                rsNewToken.put(phone,new UserEntity(rsNewUserInfo.get()));
                this.put("RSNewToken",rsNewToken);
            }
            return rsNewUserInfo.get();
        }
    }

    /**
     * 功能描述： 获取日上app首个token
     * @Param:
     * @param
     * @Return: java.lang.String
     * @Author:
     * @Date: 2022/3/21 20:41
     * @Description:
     */
    public synchronized String getRiFirstToken(){
        return this.get("RiFirstToken", String.class);
    }

    /**
    * 功能描述：设置app首个token
    * @Param:
     * @param token
    * @Return: void
    * @Author:
    * @Date: 2022/4/11 21:10
    * @Description:
    */
    public void setRiFirstToken(String token){
         this.put("RiFirstToken", token);
    }

    public String hnReLogin(String phone){
        RiOrderPhone orderPhone = riOrderPhoneService.getByPhone(phone);
        HNReq hnReq = new HNReq();
        hnReq.setPhone(orderPhone.getPhone());
        hnReq.setPassword(orderPhone.getPassword());
        return rsHaiNanService.login(hnReq);
    }

}
