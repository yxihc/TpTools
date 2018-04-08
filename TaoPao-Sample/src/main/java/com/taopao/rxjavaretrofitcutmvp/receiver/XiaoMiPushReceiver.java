package com.taopao.rxjavaretrofitcutmvp.receiver;

import android.content.Context;
import com.taopao.rxjavaretrofitcutmvp.utils.LogUtils;
import com.xiaomi.mipush.sdk.ErrorCode;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xiaomi.mipush.sdk.MiPushCommandMessage;
import com.xiaomi.mipush.sdk.MiPushMessage;
import com.xiaomi.mipush.sdk.PushMessageReceiver;

/**
 * @Author：淘跑
 * @Date: 2018/3/23 11:05
 * @Use： 小米推送自定义receiver
 * @
 * @-------------------修改记录-------------------@
 * @
 * @Modifier: 修改者  v1
 * @Data: 修改时间
 * @Version: 修改次数
 * @EditContent: 修改内容
 */

public class XiaoMiPushReceiver extends PushMessageReceiver {
    //透传消息到达客户端时调用
    //作用：可通过参数message从而获得透传消息，具体请看官方SDK文档
    @Override
    public void onReceivePassThroughMessage(Context context, MiPushMessage message) {


        //打印消息方便测试
        LogUtils.d("透传消息到达了");
        LogUtils.d("透传消息是"+message.toString());

    }


//通知消息到达客户端时调用
    //注：应用在前台时不弹出通知的通知消息到达客户端时也会回调函数
    //作用：通过参数message从而获得通知消息，具体请看官方SDK文档

    @Override
    public void onNotificationMessageArrived(Context context, MiPushMessage message) {
        //打印消息方便测试
        LogUtils.d("通知消息到达了");
        LogUtils.d("通知消息是"+message.toString());
    }

    //用户手动点击通知栏消息时调用
    //注：应用在前台时不弹出通知的通知消息到达客户端时也会回调函数
    //作用：1. 通过参数message从而获得通知消息，具体请看官方SDK文档
    //2. 设置用户点击消息后打开应用 or 网页 or 其他页面

    @Override
    public void onNotificationMessageClicked(Context context, MiPushMessage message) {

        //打印消息方便测试
        LogUtils.d("用户点击了通知消息");
        LogUtils.d("通知消息是" + message.toString());
        LogUtils.d("点击后,会进入应用" );

    }

    //用来接收客户端向服务器发送命令后的响应结果。
    @Override
    public void onCommandResult(Context context, MiPushCommandMessage message) {

        String command = message.getCommand();
        LogUtils.d(command );


        if (MiPushClient.COMMAND_REGISTER.equals(command)) {
            if (message.getResultCode() == ErrorCode.SUCCESS) {

                //打印信息便于测试注册成功与否
                LogUtils.d("注册成功");

            } else {
                LogUtils.d("注册失败");
            }
        }
    }

    //用于接收客户端向服务器发送注册命令后的响应结果。
    @Override
    public void onReceiveRegisterResult(Context context, MiPushCommandMessage message) {
        String command = message.getCommand();
        LogUtils.d(command );

        if (MiPushClient.COMMAND_REGISTER.equals(command)) {
            if (message.getResultCode() == ErrorCode.SUCCESS) {
                //打印日志：注册成功
                LogUtils.d("注册成功");
            } else {
                //打印日志：注册失败
                LogUtils.d("注册失败");
            }
        } else {
            LogUtils.d("其他情况"+message.getReason());
        }
    }

}

