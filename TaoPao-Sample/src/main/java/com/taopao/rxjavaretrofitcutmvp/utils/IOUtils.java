package com.taopao.rxjavaretrofitcutmvp.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * @Author: 淘跑
 * @Data: 2018/1/29 9:20
 * @Use: IO流工具类
 */
public class IOUtils {
    /**
     * 关闭流
     */
    public static boolean close(Closeable io) {
        if (io != null) {
            try {
                io.close();
            } catch (IOException e) {
                LogUtils.e(e);
            }
        }
        return true;
    }
}