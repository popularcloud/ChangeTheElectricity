package com.younge.changetheelectricity.changetheelectricity.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.younge.changetheelectricity.R;
import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.base.MyBaseFragment;
import com.younge.changetheelectricity.main.bean.ShopDetailBean;
import com.younge.changetheelectricity.main.presenter.ShopDetailPresenter;
import com.younge.changetheelectricity.main.view.ShopDetailView;
import com.younge.changetheelectricity.util.ImageLoaderUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ShopDetailFragment extends MyBaseFragment<ShopDetailPresenter> implements ShopDetailView {


    @BindView(R.id.tv_num)
    TextView tv_num;
    @BindView(R.id.iv_header)
    ImageView iv_header;
    @BindView(R.id.tv_count)
    TextView tv_count;
    @BindView(R.id.tv_use_time)
    TextView tv_use_time;
    @BindView(R.id.tv_phone)
    TextView tv_phone;

    String shopId;
    String macno;

    private Unbinder unbinder;
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
            mPresenter.getShopDetail("","",macno,shopId);
        }
    }

    private void initData() {
        //mPresenter.getShopLocations("","",);
    }

    public void getShopData(String shopId,String macno){
        this.shopId = shopId;
        this.macno = macno;
        mPresenter.getShopDetail("","",macno,shopId);

    }
    @Override
    protected ShopDetailPresenter createPresenter() {
        return new ShopDetailPresenter(this);
    }

    @Override
    public void onGetShopDetailSuccess(BaseModel<ShopDetailBean> data) {

        if(data != null && data.getData() != null){
            tv_num.setText(String.valueOf(data.getData().getAdmin_id()));
            if(data.getData().getDevice_goods_stats() != null ){
                List<ShopDetailBean.DeviceGoodsStatsBean> deviceGoodsStatsBeans = data.getData().getDevice_goods_stats();
                StringBuilder stringBuilder = new StringBuilder();
                for(int i = 0;i < deviceGoodsStatsBeans.size();i++){
                    stringBuilder.append(deviceGoodsStatsBeans.get(i).getTitle()+"/"+deviceGoodsStatsBeans.get(i).getNum()+"个  ");
                }
                tv_count.setText(stringBuilder.toString());
            }

            tv_phone.setText(data.getData().getTel());
            tv_use_time.setText(data.getData().getHours());

            if(data.getData().getImages() != null){
                ImageLoaderUtil.getInstance().displayFromNetDCircular(getContext(),data.getData().getImages().get(0),iv_header,R.mipmap.cte_logo);
               // ImageLoaderUtil.getInstance().displayFromNetDCircularT(this,userInfoDetail.getAvatar(),img_head,R.mipmap.default_portrait_100);
            }

        }
    }

    @Override
    public void onGetDataFail() {

    }
}
