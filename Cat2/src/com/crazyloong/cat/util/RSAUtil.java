package com.crazyloong.cat.util;

import com.crazyloong.cat.execption.RiBizExecption;


import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * @Author : crazyloongcat
 * @Date :2022/6/18 18:23
 * @Description :
 */
public class RSAUtil {

    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;
    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;

    private static final String ALGORITHM_NAME = "RSA";
    private static final String MD5_RSA = "MD5withRSA";

    public static PublicKey getPublicKey(String publicKey) {
        try {
            byte[] decodedKey = Base64.getDecoder().decode(publicKey);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decodedKey);
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_NAME);
            return keyFactory.generatePublic(keySpec);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RiBizExecption("获取密钥异常",e);
        }
    }

    /**
     * RSA加密
     *
     * @param data      待加密数据
     * @param publicKey 公钥
     */
    public static String encrypt(String data, PublicKey publicKey) throws Exception {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM_NAME);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            int inputLen = data.getBytes().length;
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int offset = 0;
            byte[] cache;
            int i = 0;
            // 对数据分段加密
            while (inputLen - offset > 0) {
                if (inputLen - offset > MAX_ENCRYPT_BLOCK) {
                    cache = cipher.doFinal(data.getBytes(), offset, MAX_ENCRYPT_BLOCK);
                } else {
                    cache = cipher.doFinal(data.getBytes(), offset, inputLen - offset);
                }
                out.write(cache, 0, cache.length);
                i++;
                offset = i * MAX_ENCRYPT_BLOCK;
            }
            byte[] encryptedData = out.toByteArray();
            out.close();
            // 获取加密内容使用base64进行编码,并以UTF-8为标准转化成字符串
            // 加密后的字符串
            return new String(Base64.getDecoder().decode(encryptedData));
        } catch (Exception e) {
            throw new RiBizExecption("加密异常",e);
        }
    }
}
