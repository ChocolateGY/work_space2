package com.talkingdata;

/**
 * Created by wangwei on 2017/7/19.
 */

import java.util.HashSet;
import java.util.Set;

public class IdValidateTool {
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean equalsIgnoreCase(String str1, String str2) {
        return str1 == null ? str2 == null : str1.equalsIgnoreCase(str2);
    }


    /***
     * 字符串最少包含几个union字符
     *
     * @param str
     * @param maxUnionSize
     * @return
     */
    public static boolean unionSize(String str, int maxUnionSize) {
        if (isEmpty(str)) return false;
        Set seet = new HashSet();
        for (int i = 0; i < str.length(); i++) {
            seet.add(str.charAt(i));
            if (seet.size() > maxUnionSize) {
                return true;
            }
        }
        return false;
    }

    /***
     * 校验mac
     *
     * @param str
     * @return
     */
    public static boolean validateMac(String str) {
        if (isEmpty(str)) {
            return false;
        }
        return str.matches("[0-9a-fA-F]{2}:[0-9a-fA-F]{2}:[0-9a-fA-F]{2}:[0-9a-fA-F]{2}:[0-9a-fA-F]{2}:[0-9a-fA-F]{2}") && unionSize(str, 2);
    }


    /***
     * 校验tdid
     *
     * @param str
     * @return
     */
    public static boolean validateTdid(String str) {
        if (isEmpty(str)) {
            return false;
        }
        return str.matches("[0-9a-zA-Z]{10,36}") && unionSize(str, 2);
    }


    /***
     * 校验imei
     *
     * @param str
     * @return
     */
    public static boolean validateImei(String str) {
        if (isEmpty(str)) {
            return false;
        }
        return str.matches("[0-9a-f]{14,16}");
    }


    /***
     * 校验androidid
     *
     * @param str
     * @return
     */
    public static boolean validateAndroidid(String str) {
        if (isEmpty(str)) {
            return false;
        }
        return str.matches("[0-9a-fA-F]{16}");
    }


    /***
     * 校验idfa
     *
     * @param str
     * @return
     */
    public static boolean validateIdfa(String str) {
        if (isEmpty(str)) {
            return false;
        }
        return str.matches("[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}") && unionSize(str, 2);
    }


    /***
     * 验证是否是合法的id类型
     *
     * @param type 类型 imei,idfa,androidid,mac,tdid
     * @param id
     * @return
     */
    public static boolean validateId(String type, String id) {
        if (isEmpty(type) || isEmpty(id) || equalsIgnoreCase(id, "null")) {
            return false;
        }


        id = id.toLowerCase();

        if (type.equalsIgnoreCase("imei")) {
            return validateImei(id);
        } else if (type.equalsIgnoreCase("idfa")) {
            return validateIdfa(id);
        } else if (type.equalsIgnoreCase("androidid")) {
            return validateAndroidid(id);
        } else if (type.equalsIgnoreCase("mac")) {
            return validateMac(id);
        } else if (type.equalsIgnoreCase("tdid")) {
            return validateTdid(id);
        }

        return false;
    }


    public static void main(String[] args) {

        System.out.println(validateId("idfa", "ef113484-7eef-4885-b625-576e1ab37c4b".toUpperCase()));
        System.out.println(validateId("imei", "861468036974662".toUpperCase()));
        System.out.println(validateId("mac", "20:5d:47:17:2c:e4".toUpperCase()));
        System.out.println(validateId("tdid", "3b61feb6c57ffbe9a1a9f56eb3b4dfd81".toUpperCase()));
        System.out.println(validateId("androidid", "aa4da5c2db632d1d".toUpperCase()));
    }
}