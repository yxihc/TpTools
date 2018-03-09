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
 * @Data: 2018/3/9 16:14
 * @Use:
 */

public class ImageAndTitleInfo {
    public String title;
    public int imageid;

    public int getImageid() {
        return imageid;
    }

    public void setImageid(int imageid) {
        this.imageid = imageid;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
