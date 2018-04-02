package com.taopao.rxjavaretrofitcutmvp.ui.model;

import com.taopao.rxjavaretrofitcutmvp.model.base.BaseResult;
import com.taopao.rxjavaretrofitcutmvp.model.response.BannerInfo;
import com.taopao.rxjavaretrofitcutmvp.model.response.ImgListInfo;

import java.util.ArrayList;

/**
 * @Author：淘跑
 * @Date: 2018/4/2 16:10
 * @Use：
 * @
 * @-------------------修改记录-------------------@
 * @
 * @Modifier: 修改者
 * @Data: 修改时间
 * @Version: 修改次数 v1
 * @EditContent: 修改内容
 */

public interface ICutMvpModel {
    void getBanner(String loaction);
    void getImgList(String url);
}
