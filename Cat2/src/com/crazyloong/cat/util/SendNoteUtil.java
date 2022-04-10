package com.crazyloong.cat.util;

import com.crazyloong.cat.execption.RiBizExecption;
import com.crazyloong.cat.pojo.PostBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author : crazyloongcat
 * @Date :2022/3/17 22:30
 * @Description : 短信发送工具类
 */
@Component
public class SendNoteUtil {
    @Autowired
    private HttpUtil httpUtil;

    private static final String NOTEURL = "/v2/sms/single_send.json";
    private static final String NOTEHOST = "sms.yunpian.com";


    public void sendNote(String context){
        PostBody<Map<String,Object>> postBody = new PostBody();
        postBody.setAPI(NOTEURL);
        postBody.setHost(NOTEHOST);
        Map<String,Object> paramters = new HashMap<>();
        paramters.put("apikey","97ad2a09e420aa2c401f5a30ddbfd6e5");
        paramters.put("mobile","18333187054");
        paramters.put("text","您的订单"+context+"已经下单成功，请稍后查看。");
        postBody.setParamters(paramters);
        try {
            // 如果失败重发三次
            String entity = null;
            for (int i = 0; i < 3; i++) {
                entity = httpUtil.doPostForNote(postBody);
                if (entity != null) {
                    break;
                }
            }
            if (entity == null) {
                throw new RiBizExecption("短信通知异常");
            }
        } catch (Exception e){
            throw new RiBizExecption("短信通知异常",e);
        }
    }
}
