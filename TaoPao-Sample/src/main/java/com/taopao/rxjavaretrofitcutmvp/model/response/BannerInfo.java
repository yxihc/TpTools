package com.taopao.rxjavaretrofitcutmvp.model.response;


/**
 * @Author: 淘跑
 * @Data: 2018/1/29 21:56
 * @Use: 轮播图
 */

public class BannerInfo {

    private int id;
    private String img;
    private String redirectContent;
    private String redirectType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getRedirectContent() {
        return redirectContent;
    }

    public void setRedirectContent(String redirectContent) {
        this.redirectContent = redirectContent;
    }

    public String getRedirectType() {
        return redirectType;
    }

    public void setRedirectType(String redirectType) {
        this.redirectType = redirectType;
    }
}
