package com.taopao.rxjavaretrofitcutmvp.utils;

import android.webkit.MimeTypeMap;

/**
 * @Author: 淘跑
 * @Data: 2018/1/29 10:50
 * @Use:
 */
public class MimeTypeUtils {

    private MimeTypeUtils() {
    }

    public static String getMimeType(final String fileName) {
        String result = "application/octet-stream";
        int extPos = fileName.lastIndexOf(".");
        if (extPos != -1) {
            String ext = fileName.substring(extPos + 1);
            result = MimeTypeMap.getSingleton().getMimeTypeFromExtension(ext);
        }
        return result;
    }
}