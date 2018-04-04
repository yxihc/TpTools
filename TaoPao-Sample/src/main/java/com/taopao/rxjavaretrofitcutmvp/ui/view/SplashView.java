package com.taopao.rxjavaretrofitcutmvp.ui.view;

import com.taopao.rxjavaretrofitcutmvp.model.response.ImgListInfo;
import com.taopao.rxjavaretrofitcutmvp.ui.base.BaseView;

/**
 * _________
 * /\        \
 * \_| Topic |
 * |       |
 * |   ____|_
 * \_/______/
 *
 * @Author: 淘跑
 * @Data: 2018/3/9 09:28
 * @Use:
 */

public interface SplashView extends BaseView {
    void onGetImgListPageResult(ImgListInfo imgListInfo);

    void onGetImgListPageError(Throwable e);
}
