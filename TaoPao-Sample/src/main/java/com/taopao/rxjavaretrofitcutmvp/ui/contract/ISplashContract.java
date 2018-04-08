package com.taopao.rxjavaretrofitcutmvp.ui.contract;

import com.taopao.rxjavaretrofitcutmvp.model.base.BaseResult;
import com.taopao.rxjavaretrofitcutmvp.model.response.BannerInfo;
import com.taopao.rxjavaretrofitcutmvp.model.response.ImgListInfo;
import com.taopao.rxjavaretrofitcutmvp.ui.base.BaseView;

import java.util.ArrayList;

/**
 * @Author：淘跑
 * @Date: 2018/4/8 21:14
 * @Use：
 * @
 * @-------------------修改记录-------------------@
 * @
 * @Modifier: 修改者  v1
 * @Data: 修改时间
 * @Version: 修改次数
 * @EditContent: 修改内容
 */

public interface ISplashContract {
    interface View extends BaseView {
        void onImgListPageResult(ImgListInfo imgListInfo);
        void onImgListPageError(String e);
    }
    interface Presenter {
        void getImagePage(String page);
    }

    interface Model {
        void getImgPage(String page,IPresenterCallBack<ImgListInfo> callBack);
    }
}
