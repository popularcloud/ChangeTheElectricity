package com.younge.changetheelectricity.changetheelectricity.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.stx.xhb.xbanner.XBanner;
import com.younge.changetheelectricity.R;
import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.base.MyBaseFragment;
import com.younge.changetheelectricity.main.bean.ShopDetailBean;
import com.younge.changetheelectricity.main.presenter.ShopDetailPresenter;
import com.younge.changetheelectricity.main.view.ShopDetailView;
import com.younge.changetheelectricity.util.SharedPreferencesUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ShopDetailFragment extends MyBaseFragment<ShopDetailPresenter> implements ShopDetailView {


    @BindView(R.id.tv_num)
    TextView tv_num;
 /*   @BindView(R.id.iv_header)
    ImageView iv_header;*/
    @BindView(R.id.xbanner)
    XBanner xBanner;
    @BindView(R.id.tv_count)
    TextView tv_count;
    @BindView(R.id.tv_use_time)
    TextView tv_use_time;
    @BindView(R.id.tv_phone)
    TextView tv_phone;
    @BindView(R.id.tv_need_account)
    TextView tv_need_account;

    String shopId;
    String macno;

    private Unbinder unbinder;
    private String isChange;

    public ShopDetailFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_shop_details, null);
        unbinder = ButterKnife.bind(this, v);
        mPresenter = createPresenter();
        initData();
        return v;
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if(isVisibleToUser && getContext() != null){
            macno = (String) SharedPreferencesUtils.getParam(getContext(),"presentMacno","");
            shopId = (String) SharedPreferencesUtils.getParam(getContext(),"presentShopId","");
            mPresenter.getShopDetail("","",macno,shopId);

            isChange = (String) SharedPreferencesUtils.getParam(getContext(),"isChange","");
            if("1".equals(isChange)){
                tv_need_account.setText("可换数量");
            }else{
                tv_need_account.setText("可充电插座数量");
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(getContext() != null){
            macno = (String) SharedPreferencesUtils.getParam(getContext(),"presentMacno","");
            shopId = (String) SharedPreferencesUtils.getParam(getContext(),"presentShopId","");
            mPresenter.getShopDetail("","",macno,shopId);

            isChange = (String) SharedPreferencesUtils.getParam(getContext(),"isChange","");
            if("1".equals(isChange)){
                tv_need_account.setText("可换数量");
            }else{
                tv_need_account.setText("可充电插座数量");
            }
        }
    }

    private void initData() {
        //mPresenter.getShopLocations("","",);

        xBanner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {

                //1、此处使用的Glide加载图片，可自行替换自己项目中的图片加载框架
                //2、返回的图片路径为Object类型，你只需要强转成你传输的类型就行，切记不要胡乱强转！

                String imgUrl = (String) model;
                Glide.with(getActivity()).load(imgUrl).placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background).into((ImageView) view);
            }
        });
    }

    public void getShopData(){
        macno = (String) SharedPreferencesUtils.getParam(getContext(),"presentMacno","");
        shopId = (String) SharedPreferencesUtils.getParam(getContext(),"presentShopId","");
        isChange = (String) SharedPreferencesUtils.getParam(getContext(),"isChange","");
        mPresenter.getShopDetail("","",macno,shopId);
        if("1".equals(isChange)){
            tv_need_account.setText("可换数量:");
        }else{
            tv_need_account.setText("可充电插座数量:");
        }

    }
    @Override
    protected ShopDetailPresenter createPresenter() {
        return new ShopDetailPresenter(this);
    }

    @Override
    public void onGetShopDetailSuccess(BaseModel<ShopDetailBean> data) {

        if(data != null && data.getData() != null){
            tv_num.setText(macno);
            List<ShopDetailBean.DeviceGoodsStatsBean> deviceGoodsStatsBeans = data.getData().getDevice_goods_stats();
            if(deviceGoodsStatsBeans != null ){
            /*    List<ShopDetailBean.DeviceGoodsStatsBean> deviceGoodsStatsBeans = data.getData().getDevice_goods_stats();
                StringBuilder stringBuilder = new StringBuilder();
                for(int i = 0;i < deviceGoodsStatsBeans.size();i++){
                    stringBuilder.append(deviceGoodsStatsBeans.get(i).getTitle()+"/"+deviceGoodsStatsBeans.get(i).getNum()+"个  ");
                }*/
                tv_count.setText(deviceGoodsStatsBeans.size()+"个");
            }else{
                tv_count.setText(0+"个");
            }

            tv_phone.setText(data.getData().getTel());
            tv_use_time.setText(data.getData().getHours());

            if(data.getData().getImages() != null){

                xBanner.setBannerData(data.getData().getImages());
               // ImageLoaderUtil.getInstance().displayFromNetDCircular(getContext(),data.getData().getImages().get(0),iv_header,R.mipmap.cte_logo);
               // ImageLoaderUtil.getInstance().displayFromNetDCircularT(this,userInfoDetail.getAvatar(),img_head,R.mipmap.default_portrait_100);
            }

        }
    }

    @Override
    public void onGetDataFail() {

    }
}
