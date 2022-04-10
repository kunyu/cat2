package com.crazyloong.cat.util;

import com.mysql.jdbc.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @Author : crazyloongcat
 * @Date :2022/3/20 21:38
 * @Description :
 */
@Component
public class LiuMingUtils {
    @Autowired
    private CacheUtil cacheUtil;


    public String randIp() {
        String ip = "";
        Long outTime = cacheUtil.get("randIpOutTime", Long.class);
        String randIpAddress = cacheUtil.get("randIpAddress", String.class);
        if (!StringUtils.isNullOrEmpty(String.valueOf(outTime)) && !StringUtils.isNullOrEmpty(randIpAddress)) {
            try {
                long nowTime = System.currentTimeMillis();
                long times = nowTime - outTime;
                if (times > 60000L) {
                    Random random = new Random(System.currentTimeMillis());
                    StringBuffer buf = new StringBuffer();
                    buf.append(random.nextInt(255) + 1);
                    buf.append(".");
                    buf.append(random.nextInt(255) + 1);
                    buf.append(".");
                    buf.append(random.nextInt(255) + 1);
                    buf.append(".");
                    buf.append(random.nextInt(255) + 1);
                    cacheUtil.put("randIpAddress", buf.toString());
                    cacheUtil.put("randIpOutTime", System.currentTimeMillis());
                    ip = buf.toString();
                    System.out.println("重新生成的ip：" + ip);
                } else {
                    ip = randIpAddress.toString();
                    System.out.println("从缓存中获取的ip：" + ip);
                }
            } catch (Exception var12) {
                System.out.println("获取随机ip异常：" + var12.getMessage());
                var12.printStackTrace();
            }
        } else {
            Random random = new Random(System.currentTimeMillis());
            StringBuffer buf = new StringBuffer();
            buf.append(random.nextInt(255) + 1);
            buf.append(".");
            buf.append(random.nextInt(255) + 1);
            buf.append(".");
            buf.append(random.nextInt(255) + 1);
            buf.append(".");
            buf.append(random.nextInt(255) + 1);
            cacheUtil.put("randIpAddress", buf.toString());
            cacheUtil.put("randIpOutTime", System.currentTimeMillis());
            ip = buf.toString();
            System.out.println("缓存ip为空，超时时间为空，重新生成的ip：" + ip);
        }

        return ip;
    }
}
