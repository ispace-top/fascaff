package com.kerwin.k_utils.sysutil;

import android.text.TextUtils;

import java.security.NoSuchAlgorithmException;

/**
 * Created by dell on 2016/9/9.
 */
public class CheckUtil {
    /**
     * 验证电话号码是否是手机号
     *
     * @param phoneNum
     * @return
     */
    public static boolean isMobileNum(String phoneNum) {
        String telRegex = "[1][3578]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(phoneNum))
            return false;
        else
            return phoneNum.matches(telRegex);
    }

    /**
     * 验证邮箱是否合法
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        String format = "^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)$";
        if (email.matches(format)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 验证用户名是否合法
     *
     * @param user
     * @return
     */
    public static boolean checkUser(String user) {
        String format = "^[a-zA-Z0-9\u4e00-\u9fa5]{3,}$";
        if (user.matches(format)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 验证密码是否合法
     *
     * @param password
     * @return
     */
    public static boolean checkPassword(String password) {
        String format = "^[a-zA-Z0-9]{6,}$";
        if (password.matches(format)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * MD加密字符串
     *
     * @param string
     * @return
     */
    public static String getMD5(String string) {
        byte[] source = string.getBytes();
        // 用来将字节转换成16进制表示的字符
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        String s = null;
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            md.update(source);
            byte tmp[] = md.digest();// MD5 的计算结果是一个 128 位的长整数，
            // 用字节表示就是 16 个字节
            char str[] = new char[16 * 2];// 每个字节用 16 进制表示的话，使用两个字符， 所以表示成 16
            // 进制需要 32 个字符
            int k = 0;// 表示转换结果中对应的字符位置
            for (int i = 0; i < 16; i++) {// 从第一个字节开始，对 MD5 的每一个字节// 转换成 16
                // 进制字符的转换
                byte byte0 = tmp[i];// 取第 i 个字节
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];// 取字节中高 4 位的数字转换,// >>>
                // 为逻辑右移，将符号位一起右移
                str[k++] = hexDigits[byte0 & 0xf];// 取字节中低 4 位的数字转换
            }
            s = new String(str);// 换后的结果转换为字符串
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return s;
    }
}
