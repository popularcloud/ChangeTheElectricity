package com.younge.changetheelectricity.login.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.stx.xhb.xbanner.XBanner;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.runtime.Permission;
import com.younge.changetheelectricity.R;
import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.base.MyBaseActivity;
import com.younge.changetheelectricity.changetheelectricity.Bean.BatteryDetailsBean;
import com.younge.changetheelectricity.changetheelectricity.adapter.BatteryDetailsAdapter;
import com.younge.changetheelectricity.main.MainActivity;
import com.younge.changetheelectricity.util.SharedPreferencesUtils;
import com.younge.changetheelectricity.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import com.younge.changetheelectricity.login.bean.LoadingImgBean;
import com.younge.changetheelectricity.login.presenter.LoadingPresenter;
import com.younge.changetheelectricity.login.view.LoadingView;

public class LoadingActivity extends MyBaseActivity<LoadingPresenter> implements LoadingView {

    @BindView(R.id.xbanner)
    XBanner xBanner;
    @BindView(R.id.tv_go_in)
    TextView tv_go_in;

    private String [] permissions = new String[]{
            Permission.CAMERA,
            Permission.READ_EXTERNAL_STORAGE,
            Permission.WRITE_EXTERNAL_STORAGE,
            Permission.ACCESS_COARSE_LOCATION,
            Permission.ACCESS_FINE_LOCATION,
            Permission.READ_PHONE_STATE,
            Permission.CALL_PHONE,
            Permission.GET_ACCOUNTS,
            Permission.READ_PHONE_NUMBERS};

    private BatteryDetailsAdapter mAdapter;

    private List<BatteryDetailsBean> allList = new ArrayList<>();

    private List<LoadingImgBean> loadingImgBeans = new ArrayList<>();

    @Override
    protected LoadingPresenter createPresenter() {
        return new LoadingPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentViewId(Bundle savedInstanceState) {
        return R.layout.activity_loading;
    }

    @Override
    protected void init() {

    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String token = (String) SharedPreferencesUtils.getParam(LoadingActivity.this,"token","");

            if(TextUtils.isEmpty(token)){
                startActivity(new Intent(LoadingActivity.this, LoginActivity.class));
            }else{
                startActivity(new Intent(LoadingActivity.this, MainActivity.class));
            }

            finish();
        }
    };


    @Override
    protected void initGetData() {
        mPresenter.getLoadingImage();
        AndPermission.with(LoadingActivity.this)
                .runtime()
                .permission(permissions)
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                       // mHandler.sendEmptyMessageDelayed(1, 5*1000);
                    }
                })
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                       // mHandler.sendEmptyMessageDelayed(1, 5*1000);
                    }
                }).start();
    }




    private void gotoMain(){

        Observable.timer(2000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
                d.dispose();
              //  onNext(0L);
                Log.e("rxlog","onSubscribe");
            }

            @Override
            public void onNext(Long aLong) {
                Log.e("rxlog","onNext");
                String token = (String) SharedPreferencesUtils.getParam(LoadingActivity.this,"token","");

                if(TextUtils.isEmpty(token)){
                    startActivity(new Intent(LoadingActivity.this, LoginActivity.class));
                }else{
                    startActivity(new Intent(LoadingActivity.this, MainActivity.class));
                }

                finish();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("rxlog","onError"+e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.e("rxlog","onComplete");
            }
        });
    }

    @Override
    protected void widgetListener() {
        //加载广告图片
        xBanner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {

                //1、此处使用的Glide加载图片，可自行替换自己项目中的图片加载框架
                //2、返回的图片路径为Object类型，你只需要强转成你传输的类型就行，切记不要胡乱强转！
                LoadingImgBean loadingImgBean = (LoadingImgBean) model;
                Glide.with(LoadingActivity.this).load(loadingImgBean.getImage()).placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background).into((ImageView) view);

                if(position == loadingImgBeans.size() -1 && position != 0){
                    tv_go_in.setVisibility(View.VISIBLE);
                    tv_go_in.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String token = (String) SharedPreferencesUtils.getParam(LoadingActivity.this,"token","");

                            if(TextUtils.isEmpty(token)){
                                startActivity(new Intent(LoadingActivity.this, LoginActivity.class));
                            }else{
                                startActivity(new Intent(LoadingActivity.this, MainActivity.class));
                            }
                        }
                    });
                }
            }
        });

        xBanner.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override
            public void onItemClick(XBanner banner, Object model,View view, int position) {
                //Toast.makeText(LoadingActivity.this, "点击了第"+position+"图片", Toast.LENGTH_SHORT).show();
                String token = (String) SharedPreferencesUtils.getParam(LoadingActivity.this,"token","");

                if(TextUtils.isEmpty(token)){
                    startActivity(new Intent(LoadingActivity.this, LoginActivity.class));
                }else{
                    startActivity(new Intent(LoadingActivity.this, MainActivity.class));
                }

                finish();
            }
        });
    }


    @Override
    public void onGetDataSuccess(BaseModel<List<LoadingImgBean>> data) {
        if (data != null) {
            loadingImgBeans = (List<LoadingImgBean>) data.getData();
            xBanner.setBannerData(loadingImgBeans);
        }

        //gotoMain();
    }

    @Override
    public void onGetDataFail() {
        ToastUtil.makeText(LoadingActivity.this,"获取轮播图失败");
    }
}
