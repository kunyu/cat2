package com.crazyloong.cat.util;

import com.alibaba.fastjson.JSONObject;
import com.crazyloong.cat.pojo.GetBody;
import com.crazyloong.cat.pojo.PostBody;
import com.crazyloong.cat.rshainan.constant.RishangHNEnum;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class HttpUtil {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 同步get方式的请求
     * @param getBody
     * @throws ClientProtocolException
     * @throws IOException
     */
    public String doGet(GetBody getBody,Enum type) throws Exception {
        UrlBuilder urlBuilder = new UrlBuilder(getBody);
        //执行
        HttpUriRequest httpGet = new HttpGet(urlBuilder.toString());
        if (RishangHNEnum.GetType.AUTHORIZATION.equals(type)) {
            httpGet.addHeader("Authorization",getBody.getAuthorization());
        }
        if (RishangHNEnum.GetType.TOKEN.equals(type)) {
            httpGet.addHeader("token",getBody.getAuthorization());
        }
        CloseableHttpResponse response = null;
        try {
            response = HttpConnectionPoolUtil.getHttpClient(urlBuilder.toString()).execute(httpGet);
            if (response.getEntity() != null) {
                String entityStr = EntityUtils.toString(response.getEntity(),"utf-8");
                logger.debug("entityStr:"+entityStr);
                return entityStr;
            }
        } catch (Exception e) {
            logger.error("request error:",e);
            throw e;
        } finally {
            try{
                if (response != null) response.close();
            } catch (IOException e) {
                logger.error("CloseableHttpResponse close failed:",e);
            }
        }
        return null;
    }

    /**
     * 同步post请求方式： 请求中需要带的数据通过
     * @throws ClientProtocolException
     * @throws IOException
     */
    public String doPost(PostBody postBody,Enum type) throws Exception{
        UrlBuilder urlBuilder = new UrlBuilder(postBody);
        HttpPost httpPost= new HttpPost(urlBuilder.toString());
        String json = JSONObject.toJSONString(postBody.getParamters());
        httpPost.setEntity(new StringEntity(json, "utf-8"));
        if (RishangHNEnum.GetType.AUTHORIZATION.equals(type)) {
            httpPost.addHeader("Authorization",postBody.getAuthorization());
        }
        if (RishangHNEnum.GetType.TOKEN.equals(type)) {
            httpPost.addHeader("token",postBody.getAuthorization());
            httpPost.addHeader("stockId",postBody.getStockId());
        }
        httpPost.addHeader("Content-Type", "application/json");
        httpPost.addHeader("Accept", "*/*");
        httpPost.addHeader("User-Agent", "Mozilla/5.0 (Linux; Android 7.1.2; SM-N976N Build/QP1A.190711.020; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/75.0.3770.143 Mobile Safari/537.36 Html5Plus/1.0");
        CloseableHttpResponse response = null;
        try {
            response = HttpConnectionPoolUtil.getHttpClient(urlBuilder.toString()).execute(httpPost);
            if (response.getEntity() != null) {
                String entityStr = EntityUtils.toString(response.getEntity(),"utf-8");
                logger.debug("entityStr:"+entityStr);
                return entityStr;
            }
        } catch (Exception e) {
            logger.error("request error:",e);
            throw e;
        } finally {
            try{
                if (response != null) response.close();
            } catch (IOException e) {
                logger.error("CloseableHttpResponse close failed:",e);
            }
        }
        return null;
    }

    /**
     * 同步post请求方式： 请求中需要带的数据通过
     * @throws ClientProtocolException
     * @throws IOException
     */
    public String doHNPost(GetBody getBody) throws Exception{
        UrlBuilder urlBuilder = new UrlBuilder(getBody);
        HttpPost httpPost= new HttpPost(urlBuilder.toString());
        httpPost.addHeader("token",getBody.getAuthorization());
        httpPost.addHeader("Content-Type", "application/json");
        httpPost.addHeader("Accept", "*/*");
        CloseableHttpResponse response = null;
        try {
            response = HttpConnectionPoolUtil.getHttpClient(urlBuilder.toString()).execute(httpPost);
            if (response.getEntity() != null) {
                String entityStr = EntityUtils.toString(response.getEntity(),"utf-8");
                logger.debug("entityStr:"+entityStr);
                return entityStr;
            }
        } catch (Exception e) {
            logger.error("request error:",e);
            throw e;
        } finally {
            try{
                if (response != null) response.close();
            } catch (IOException e) {
                logger.error("CloseableHttpResponse close failed:",e);
            }
        }
        return null;
    }

    /**
     * 同步post请求方式： 请求中需要带的数据通过
     * @throws ClientProtocolException
     * @throws IOException
     */
    public String doPostForNote(PostBody postBody) throws Exception{
        UrlBuilder urlBuilder = new UrlBuilder(postBody);
        HttpPost httpPost= new HttpPost(urlBuilder.toString());
        String json = JSONObject.toJSONString(postBody.getParamters());
        httpPost.setEntity(new StringEntity(json, "utf-8"));
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8;");
        httpPost.addHeader("Accept", "application/json;charset=utf-8;");
        CloseableHttpResponse response = null;
        try {
            response = HttpConnectionPoolUtil.getHttpClient(urlBuilder.toString()).execute(httpPost);
            if (response.getEntity() != null) {
                String entityStr = EntityUtils.toString(response.getEntity(),"utf-8");
                logger.debug("entityStr:"+entityStr);
                return entityStr;
            }
        } catch (Exception e) {
            logger.error("request error:",e);
            throw e;
        } finally {
            try{
                if (response != null) response.close();
            } catch (IOException e) {
                logger.error("CloseableHttpResponse close failed:",e);
            }
        }
        return null;
    }

}
