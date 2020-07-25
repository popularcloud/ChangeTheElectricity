package module.login.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.stx.xhb.xbanner.XBanner;
import com.younge.changetheelectricity.R;
import com.younge.changetheelectricity.base.BaseActivity;
import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.base.MyBaseActivity;
import com.younge.changetheelectricity.changetheelectricity.Bean.BatteryDetailsBean;
import com.younge.changetheelectricity.changetheelectricity.adapter.BatteryDetailsAdapter;
import com.younge.changetheelectricity.main.MainActivity;
import com.younge.changetheelectricity.net.ApiDns;
import com.younge.changetheelectricity.net.ApiServer;
import com.younge.changetheelectricity.util.ToastUtil;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import module.login.bean.LoadingImgBean;
import module.login.presenter.LoadingPresenter;
import module.login.view.LoadingView;

public class LoadingActivity extends MyBaseActivity<LoadingPresenter> implements LoadingView {

    @BindView(R.id.xbanner)
    XBanner xBanner;

    private BatteryDetailsAdapter mAdapter;

    private List<BatteryDetailsBean> allList = new ArrayList<>();

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


    @Override
    protected void initGetData() {
        mPresenter.getLoadingImage();

        Observable.timer(10, TimeUnit.SECONDS).subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Long aLong) {

            }

            @Override
            public void onError(Throwable e) {}

            @Override
            public void onComplete() {}
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
            }
        });

        xBanner.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override
            public void onItemClick(XBanner banner, Object model,View view, int position) {
                Toast.makeText(LoadingActivity.this, "点击了第"+position+"图片", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoadingActivity.this, LoginActivity.class));
            }
        });
    }

    @Override
    public void onGetDataSuccess(BaseModel<List<LoadingImgBean>> data) {
        if(data != null){
            List<LoadingImgBean> loadingImgBeans = (List<LoadingImgBean>) data.getData();
            xBanner.setBannerData(loadingImgBeans);
        }

    }

    @Override
    public void onGetDataFail() {
        ToastUtil.makeText(LoadingActivity.this,"获取轮播图失败");
    }
}
