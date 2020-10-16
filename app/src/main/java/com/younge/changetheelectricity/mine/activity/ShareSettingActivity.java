package com.younge.changetheelectricity.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.younge.changetheelectricity.R;
import com.younge.changetheelectricity.base.BaseActivity;
import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.base.MyBaseActivity;
import com.younge.changetheelectricity.mine.bean.ShareSettingBean;
import com.younge.changetheelectricity.mine.presenter.ShareSettingPresenter;
import com.younge.changetheelectricity.mine.view.ShareView;
import com.younge.changetheelectricity.net.ApiRetrofit;
import com.younge.changetheelectricity.util.ImageLoaderUtil;
import com.younge.changetheelectricity.util.SharedPreferencesUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShareSettingActivity extends MyBaseActivity<ShareSettingPresenter> implements ShareView {


    @BindView(R.id.tv_center_title)
    TextView tv_center_title;
    @BindView(R.id.iv_bg)
    ImageView iv_bg;
    private UMImage image;


    @Override
    protected ShareSettingPresenter createPresenter() {
        return new ShareSettingPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tv_center_title.setText("邀请好友");

    }

    @Override
    protected int getContentViewId(Bundle savedInstanceState) {
        return R.layout.activity_share_setting;
    }

    @Override
    protected void init() {

        mPresenter.getShareSetting((String) SharedPreferencesUtils.getParam(this,"token",""));
    }

    @Override
    protected void initGetData() {

    }

    @Override
    protected void widgetListener() {

    }

    @OnClick({R.id.rl_fanhui_left,R.id.ll_share_wechat,R.id.ll_share_wechat_circle})
    public void onBtnClick(View view){
        switch (view.getId()){
            case R.id.rl_fanhui_left:
                finish();
                break;
            case R.id.ll_share_wechat:
                if(image == null){
                    image = new UMImage(ShareSettingActivity.this, R.mipmap.ic_share_setting);//资源文件
                }
                new ShareAction(ShareSettingActivity.this)
                        .setPlatform(SHARE_MEDIA.WEIXIN)//传入平台
                        .withMedia(image)
                        .setCallback(shareListener)//回调监听器
                        .share();
                break;
            case R.id.ll_share_wechat_circle:
                if(image == null){
                    image = new UMImage(ShareSettingActivity.this, R.mipmap.ic_share_setting);//资源文件
                }
                new ShareAction(ShareSettingActivity.this)
                        .setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)//传入平台
                        .withMedia(image)
                        .setCallback(shareListener)//回调监听器
                        .share();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(ShareSettingActivity.this,"成功了",Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(ShareSettingActivity.this,"失败"+t.getMessage(),Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(ShareSettingActivity.this,"取消了",Toast.LENGTH_LONG).show();

        }
    };

    @Override
    public void onGetShareSuccess(BaseModel<ShareSettingBean> data) {

        if(data != null && data.getData() != null){
            ImageLoaderUtil.getInstance().displayFromNet(ShareSettingActivity.this, data.getData().getShare_image(),iv_bg,R.mipmap.ic_share_setting);

            if(!TextUtils.isEmpty(data.getData().getShare_image())){
                image = new UMImage(ShareSettingActivity.this,data.getData().getShare_image());
            }
        }

    }

    @Override
    public void onGetDataFail() {

    }
}
