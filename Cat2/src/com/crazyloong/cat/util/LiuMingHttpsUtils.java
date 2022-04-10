package com.crazyloong.cat.util;

import com.mysql.jdbc.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.net.ssl.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.SecureRandom;

/**
 * @Author : crazyloongcat
 * @Date :2022/3/20 21:34
 * @Description :
 */
@Component
public class LiuMingHttpsUtils {
    @Autowired
    private LiuMingUtils liuMingUtils;

    public String httpRequest(String requestUrl, String token) {
        StringBuffer buffer = null;
        BufferedReader bufferedReader = null;
        InputStreamReader inputStreamReader = null;
        InputStream inputStream = null;
        HttpsURLConnection httpUrlConn = null;

        try {
            TrustManager[] tm = new TrustManager[]{new CDFX509TrustManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init((KeyManager[])null, tm, new SecureRandom());
            SSLSocketFactory ssf = sslContext.getSocketFactory();
            URL url = new URL(requestUrl);
            httpUrlConn = (HttpsURLConnection)url.openConnection();
            httpUrlConn.setSSLSocketFactory(ssf);
            httpUrlConn.setRequestProperty("User-Agent", "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.45 Mobile Safari/537.36");
            String ip = liuMingUtils.randIp();
            httpUrlConn.setRequestProperty("X-Forwarded-For", ip);
            httpUrlConn.setRequestProperty("HTTP_X_FORWARDED_FOR", ip);
            httpUrlConn.setRequestProperty("HTTP_CLIENT_IP", ip);
            httpUrlConn.setRequestProperty("REMOTE_ADDR", ip);
            if (!StringUtils.isNullOrEmpty(token)) {
                httpUrlConn.setRequestProperty("token", token);
                httpUrlConn.setRequestProperty("stockid", "6922");
                httpUrlConn.setRequestProperty("terminalid", "6");
            }

            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            httpUrlConn.setRequestMethod("GET");
            inputStream = httpUrlConn.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            bufferedReader = new BufferedReader(inputStreamReader);
            buffer = new StringBuffer();
            String str = null;

            while((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
        } catch (Exception var29) {
            var29.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException var28) {
                    var28.printStackTrace();
                }
            }

            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (IOException var27) {
                    var27.printStackTrace();
                }
            }

            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException var26) {
                    var26.printStackTrace();
                }
            }

            if (httpUrlConn != null) {
                httpUrlConn.disconnect();
            }

        }

        return buffer.toString();
    }
}
