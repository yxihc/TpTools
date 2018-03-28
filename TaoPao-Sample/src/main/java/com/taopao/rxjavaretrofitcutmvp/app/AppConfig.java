package com.taopao.rxjavaretrofitcutmvp.app;

import android.os.Environment;

import com.taopao.rxjavaretrofitcutmvp.R;
import com.taopao.rxjavaretrofitcutmvp.utils.UIUtils;
import java.io.File;

/**
 * @Author：淘跑
 * @Date: 2018/3/27 08:33
 * @Use： 文件位置及app的一些设置
 * @
 * @-------------------修改记录-------------------@
 * @
 * @Modifier: 修改者  v1
 * @Data: 修改时间
 * @Version: 修改次数
 * @EditContent: 修改内容
 */
public class AppConfig {
    /**
     * 文件存储根目录(最后有 "/")
     */
    public final static String fileRoot= Environment.getExternalStorageDirectory()+ File.separator+ UIUtils.getString(R.string.app_name)+File.separator;
}
