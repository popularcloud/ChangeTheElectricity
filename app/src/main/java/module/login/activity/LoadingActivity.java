package module.login.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lzy.okgo.OkGo;
import com.stx.xhb.xbanner.XBanner;
import com.younge.changetheelectricity.R;
import com.younge.changetheelectricity.base.BaseActivity;
import com.younge.changetheelectricity.base.BaseBean;
import com.younge.changetheelectricity.base.NetRequestUrl;
import com.younge.changetheelectricity.changetheelectricity.Bean.BatteryDetailsBean;
import com.younge.changetheelectricity.changetheelectricity.adapter.BatteryDetailsAdapter;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import module.login.bean.LoadingImgBean;

public class LoadingActivity extends BaseActivity {

    @BindView(R.id.xbanner)
    XBanner xBanner;

    private BatteryDetailsAdapter mAdapter;

    private List<BatteryDetailsBean> allList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        ButterKnife.bind(this);

        init();
    }

    private void init() {

        //加载广告图片
        xBanner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {

                //1、此处使用的Glide加载图片，可自行替换自己项目中的图片加载框架
                //2、返回的图片路径为Object类型，你只需要强转成你传输的类型就行，切记不要胡乱强转！
                LoadingImgBean loadingImgBean = (LoadingImgBean) model;
                Glide.with(LoadingActivity.this).load(loadingImgBean.getXBannerUrl()).placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background).into((ImageView) view);
            }
        });

        getBannerData();
    }

    private void getBannerData(){
   /*     EasyHttp.getInstance().setBaseUrl(NetRequestUrl.baseUrl).post("/api/index").params("HTTP_API","vv/cms/api/index/banner")
                    .execute(new SimpleCallBack<Object>() {

                        @Override
                        public void onError(ApiException e) {
                            ToastUtil.makeText(LoadingActivity.this,"获取启动图信息失败"+e.getCode());
                            e.printStackTrace();
                        }

                        @Override
                        public void onSuccess(Object loadingImgBeanBaseBean) {
                            //xBanner.setBannerData(loadingImgBeanBaseBean.getData());
                        }
                    });*/
        OkGo.post("http://winpower.wljueli.com/api/index").


    }

}
