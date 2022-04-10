package com.crazyloong.cat.util;

import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * @Author : crazyloongcat
 * @Date :2022/3/20 21:49
 * @Description :
 */
public class CDFX509TrustManager implements X509TrustManager {
    public CDFX509TrustManager() {
    }

    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
    }

    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
    }

    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }
}
