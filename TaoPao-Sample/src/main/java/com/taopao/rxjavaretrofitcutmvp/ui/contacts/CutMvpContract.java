package com.taopao.rxjavaretrofitcutmvp.ui.contacts;

import com.taopao.rxjavaretrofitcutmvp.model.base.BaseResult;
import com.taopao.rxjavaretrofitcutmvp.model.response.BannerInfo;
import com.taopao.rxjavaretrofitcutmvp.model.response.ImgListInfo;
import com.taopao.rxjavaretrofitcutmvp.ui.base.BaseModel;
import com.taopao.rxjavaretrofitcutmvp.ui.base.BaseView;
import java.util.ArrayList;

/**
 * @Author： 淘跑
 * @Date: 2018/4/4 13:59
 * @Use：
 * @
 * @-------------------修改记录-------------------@
 * @
 * @Modifier: 修改者
 * @Data: 修改时间
 * @Version: 修改次数 v1
 * @EditContent: 修改内容
 */

public interface CutMvpContract {

    interface View extends BaseView {
        void onBannerResult(BaseResult<ArrayList<BannerInfo>> banner);

        void onImgListResult(ImgListInfo imgListInfo);
    }

    interface Presenter {
        void getBanner(String loaction);

        void getImgList(String url);
    }

    interface Model extends BaseModel{

        void getBanner(String loaction,IPresenterCallBack<BaseResult<ArrayList<BannerInfo>>> callBack);

        void getImgList(String url,IPresenterCallBack<ImgListInfo> callBack);

    }

}
