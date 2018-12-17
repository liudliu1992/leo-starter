package com.leo.util.encode;

import java.security.MessageDigest;

/**
 * MD5签名，加密解密
 *
 * @author 刘绍林
 * @create 2017-10-13 17:25
 **/
public class MD5Convert {

    /***
     * MD5加密 生成32位md5码
     */
    public static String string2MD5(String inStr) {
        MessageDigest md5;
        String result ="";
        try {
            md5 = MessageDigest.getInstance("MD5");
            char[] charArray = inStr.toCharArray();
            byte[] byteArray = new byte[charArray.length];

            for (int i = 0; i < charArray.length; i++) {
                byteArray[i] = (byte) charArray[i];
            }
            byte[] md5Bytes = md5.digest(byteArray);
            StringBuffer hexValue = new StringBuffer();
            for (int i = 0; i < md5Bytes.length; i++) {
                int val = ((int) md5Bytes[i]) & 0xff;
                if (val < 16) {
                    hexValue.append("0");
                }
                hexValue.append(Integer.toHexString(val));
            }
            result=hexValue.toString();
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }
        return result;
    }
}
