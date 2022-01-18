package com.crazyloong.cat.util;

import com.alibaba.fastjson.JSONObject;
import com.crazyloong.cat.pojo.GetBody;
import com.crazyloong.cat.pojo.PostBody;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class HttpUtil {



    /**
     * 同步get方式的请求
     * @param getBody
     * @throws ClientProtocolException
     * @throws IOException
     */
    public HttpEntity doGet(GetBody getBody) throws ClientProtocolException, IOException {
        //创建CloseableHttpClient
        HttpClientBuilder builder = HttpClientBuilder.create();
        CloseableHttpClient client = builder.build();
        UrlBuilder urlBuilder = new UrlBuilder(getBody);
        //执行
        HttpUriRequest httpGet = new HttpGet(urlBuilder.toString());
        httpGet.addHeader("Authorization",getBody.getAuthorization());
        CloseableHttpResponse response = client.execute(httpGet);
        return response.getEntity();
    }

    /**
     * 同步post请求方式： 请求中需要带的数据通过
     * @throws ClientProtocolException
     * @throws IOException
     */
    public HttpEntity doPost(PostBody postBody) throws ClientProtocolException, IOException{
        HttpClientBuilder builder = HttpClientBuilder.create();
        CloseableHttpClient client = builder.build();

        UrlBuilder urlBuilder = new UrlBuilder(postBody);
        HttpPost httpPost= new HttpPost(urlBuilder.toString());
        String json = JSONObject.toJSONString(postBody.getParamters());
        httpPost.setEntity(new StringEntity(json, "utf-8"));
        httpPost.addHeader("Content-Type", "application/json");
        httpPost.addHeader("Accept", "application/json");
        httpPost.addHeader("User-Agent", "Mozilla/5.0 (Linux; Android 7.1.2; SM-N976N Build/QP1A.190711.020; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/75.0.3770.143 Mobile Safari/537.36 Html5Plus/1.0");
        httpPost.addHeader("Content-Type", "application/json");
        httpPost.addHeader("Authorization",postBody.getAuthorization());
        CloseableHttpResponse response = client.execute(httpPost);
        return response.getEntity();
    }


    public static void main(String[] args) throws Exception{
        HttpUtil util = new HttpUtil();
        PostBody postBody = new PostBody();
        postBody.setAuthorization("Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1aWQiOjU2NTU0NDcsImR2IjoiIiwicmQiOiIyMDIxMDgyNDIyMzIzOTc3NCIsInZpcCI6IiJ9.ol_vMWoxnEiZ5ggDXaXiMTbF8-BhNlMRXFcWNnt9xxU");
        postBody.setAPI("/goods/shopcart");
        postBody.setHost("srmemberapp2.srgow.com");
        Map paramters = new HashMap();
        paramters.put("abiid","76701");
        paramters.put("num",2);
        postBody.setParamters(paramters);
        util.doPost(postBody);
    }
}
