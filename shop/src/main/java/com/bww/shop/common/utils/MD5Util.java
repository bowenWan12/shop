package com.bww.shop.common.utils;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * 用户 加密工具
 */
public class MD5Util {

    public static String md5(String src){
        return DigestUtils.md5Hex(src);
    }

    // 用于生成MD5 密码的工具类
    private  static final String salt="1a2b3c4d";


    /** 第一次md5 ：
     * 用于 通过输入的密码生成 传输的密码 ：方法 通过固定盐值和明文密码之间的拼接在生成md5
     */
    public static String inputPassToFormPass(String inputPass){
        String str=""+salt.charAt(0)+salt.charAt(2)+inputPass+salt.charAt(5)+salt.charAt(4);//12123456c3
        return md5(str);
    }
    /**
     * 第二次md5 : 通过输入的密码和数据库随机盐值 继续生成 密码
     */
    public static String fromPassToDBPass(String fromPass,String salt){
        String str=""+salt.charAt(0)+salt.charAt(2)+fromPass+salt.charAt(5)+salt.charAt(4);//12123456c3
        return md5(str);
    }
    public  static String inputPassToDBPass(String inputPass,String saltDB){
        String formPass = inputPassToFormPass(inputPass);
        String dbPass = fromPassToDBPass(formPass, salt);
        return  dbPass;
    }
    public static void main(String[] args) {
        System.out.println(inputPassToDBPass("xinxin","043PbaGj288tlA0KPTGj2I4UFj2PbaGp"));
        System.out.println("0bcd465ead92d16cf518ae662fe08deb");
    }

}