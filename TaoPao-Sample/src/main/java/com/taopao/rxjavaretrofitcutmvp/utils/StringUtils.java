package com.taopao.rxjavaretrofitcutmvp.utils;

import java.util.List;
/**
 * ━━━━━━神兽出没━━━━━━
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　　　　　┃
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　　　　　┃
 * 　　┃　　　┻　　　┃
 * 　　┃　　　　　　　┃
 * 　　┗━┓　　　┏━┛
 * 　　　　┃　　　┃  神兽保佑
 * 　　　　┃　　　┃  代码无bug
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　     ┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * ━━━━━━感觉萌萌哒━━━━━━
 *
 * @Author: 淘跑
 * @Data: 2018/1/29 10:50
 * @Use: 字符串工具类
 */
public class StringUtils {
    /**
     * 得到不为空的字符串
     *
     * @param o
     * @return
     */
    public static String getNotNULLStr(Object o) {
        return o == null ? "" : o.toString();
    }

    /**
     * 判断字符串或集合是否为空
     *
     * @param o
     * @return
     */
    public static boolean isEmpty(Object o) {
        if (o instanceof List)
            return ((List) o).size() == 0;
        return o == null || o.toString().equals("");
    }

    /**
     * 判断字符串是否为空（空格字符串也是blank）
     *
     * @param s
     * @return
     */
    public static boolean isBlank(final CharSequence s) {
        if (s == null) {
            return true;
        }
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 函数传入汉字的Unicode编码字符串，返回相应的汉字字符串
     *
     * @return
     */
    public static String decodeUnicode(final String utfString) {
        StringBuilder sb = new StringBuilder();
        int i = -1;
        int pos = 0;

        while ((i = utfString.indexOf("\\u", pos)) != -1) {
            sb.append(utfString.substring(pos, i));
            if (i + 5 < utfString.length()) {
                pos = i + 6;
                sb.append((char) Integer.parseInt(utfString.substring(i + 2, i + 6), 16));
            }
        }
        sb.append(utfString.substring(pos, utfString.length()));

        return sb.toString();
    }

}