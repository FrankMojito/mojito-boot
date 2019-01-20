package com.mojito.mojitoboot.common.utils.fileRenderUtil;

import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Auther: Mojito
 * @Date: 2019/1/20 13:46
 * @Description:
 */
public class EncodeByMD5Util {
    public static String EncodeByMD5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64Encoder = new BASE64Encoder();
        String encodeStr = base64Encoder.encode(md5.digest(str.getBytes("UTF-8")));
        return encodeStr;
    }
}
