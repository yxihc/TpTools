package com.taopao.rxjavaretrofitcutmvp.model.response;

/**
 * _________
 * /\        \
 * \_| Topic |
 * |       |
 * |   ____|_
 * \_/______/
 *
 * @Author: 淘跑
 * @Data: 2018/2/28 13:50
 * @Use: 文件上传回调
 */

public class UpLoadResult {

    private String id;
    private String path;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }
}
