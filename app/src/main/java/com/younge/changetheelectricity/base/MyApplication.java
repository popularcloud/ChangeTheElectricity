package com.younge.changetheelectricity.base;

import android.app.Application;

import androidx.multidex.MultiDex;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

public class MyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        // 初始化MultiDex
        MultiDex.install(this);
        //注册微信
   /*     IWXAPI mWxApi = WXAPIFactory.createWXAPI(this, "wx197fcf39e65d69a9", true);
        mWxApi.registerApp("wx197fcf39e65d69a9");*/

        //初始化二维码扫描
       // ZXingLibrary.initDisplayOpinion(this);

        initLogger();
        initUmeng();
    }


    //日志
    private void initLogger() {
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)  // 是否显示线程信息 默认显示 上图Thread Infrom的位置
                .methodCount(0)         // 展示方法的行数 默认是2  上图Method的行数
                .methodOffset(7)        // 内部方法调用向上偏移的行数 默认是0
                .tag("Logger tag")   // 自定义全局tag 默认：PRETTY_LOGGER
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy) {
            @Override
            public boolean isLoggable(int priority, String tag) {
                return true;
            }
        });
    }

    private void initUmeng() {
        UMConfigure.init(this,"5f0d9287dbc2ec0841e9cad0","umeng",UMConfigure.DEVICE_TYPE_PHONE,"");
        UMConfigure.setLogEnabled(true);


        //友盟分享
        // 微信 appid appsecret
        PlatformConfig.setWeixin("wx197fcf39e65d69a9", "ebb31de9cb77fcff21256d2f2f593580");
    }

}