package com.younge.changetheelectricity.main.fragment;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.orhanobut.logger.Logger;
import com.younge.changetheelectricity.R;
import com.younge.changetheelectricity.base.BaseFragment;
import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.base.MyBaseFragment;
import com.younge.changetheelectricity.base.MyConstants;
import com.younge.changetheelectricity.changetheelectricity.activity.BatteryDetailActivity;
import com.younge.changetheelectricity.changetheelectricity.activity.ChargeDetailActivity;
import com.younge.changetheelectricity.changetheelectricity.fragment.BatteryDetailsFragment;
import com.younge.changetheelectricity.changetheelectricity.fragment.ChargeDetailsFragment;
import com.younge.changetheelectricity.changetheelectricity.fragment.ShopDetailFragment;
import com.younge.changetheelectricity.main.MainActivity;
import com.younge.changetheelectricity.main.adapter.MyPagerAdapter;
import com.younge.changetheelectricity.main.bean.ShopDetailLocationBean;
import com.younge.changetheelectricity.main.presenter.MainPresenter;
import com.younge.changetheelectricity.main.view.MainView;
import com.younge.changetheelectricity.util.SharedPreferencesUtils;
import com.younge.changetheelectricity.widget.CustomViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainChargeFragment extends MyBaseFragment<MainPresenter> implements MainView{

    @BindView(R.id.map)
    MapView mMapView;
    @BindView(R.id.cvp_data)
    CustomViewPager cvp_data;
    @BindView(R.id.tv_scan)
    TextView tv_scan;

    @BindView(R.id.tv_changeElectricity)
    TextView tv_changeElectricity;
    @BindView(R.id.tv_chargeElectricity)
    TextView tv_chargeElectricity;

    @BindView(R.id.tv_battery_detail_txt)
    TextView tv_battery_detail_txt;
    @BindView(R.id.tv_shop_detail_txt)
    TextView tv_shop_detail_txt;

    @BindView(R.id.tv_show_hide)
    TextView tv_show_hide;
    @BindView(R.id.ll_no_select_show)
    LinearLayout ll_no_select_show;
    @BindView(R.id.iv_shop)
    ImageView iv_shop;
    @BindView(R.id.ll_shop)
    LinearLayout ll_shop;
    @BindView(R.id.ll_select_show)
    LinearLayout ll_select_show;

    @BindView(R.id.tv_shop_name)
    TextView tv_shop_name;
    @BindView(R.id.tv_shop_address)
    TextView tv_shop_address;

    private boolean isShow = false;
    private boolean isShop = false;

    private List<Fragment> fragmentList = new ArrayList<>();

    private MyLocationStyle myLocationStyle;

    List<ShopDetailLocationBean.ListBean> listBeans;

    private ShopDetailLocationBean.ListBean presentShop;

    //初始化地图控制器对象
    AMap aMap = null;

    private double presentLongitude;
    private double presentLatitude;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_charge, null);
        ButterKnife.bind(this, view);
        mMapView.onCreate(savedInstanceState);

        tv_battery_detail_txt.setText("充电详情");

        mPresenter = createPresenter();

        initMapView();
        initViewpager();
        return view;
    }

    private void initMapView() {

        if (aMap == null) {
            aMap = mMapView.getMap();
        }


        aMap.setOnMyLocationChangeListener(new AMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {
                presentLongitude = location.getLongitude();
                presentLatitude = location.getLatitude();
                Logger.e("获取到了定位信息"+"========经度:"+ presentLongitude + "=========维度："+presentLatitude);
                mPresenter.getShopLocations(String.valueOf(presentLongitude),String.valueOf(presentLatitude));
            }
        });

        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_FOLLOW) ;//连续定位、且将视角移动到地图中心点，定位蓝点跟随设备移动。（1秒1次定位）
        myLocationStyle.interval(10000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        // myLocationStyle.showMyLocation(true);
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        //aMap.getUiSettings().setMyLocationButtonEnabled(true); //设置默认定位按钮是否显示，非必需设置。
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。

        //mPresenter.getShopLocations(String.valueOf(location.getLongitude()),String.valueOf(location.getLatitude()));
        //mPresenter.getShopLocations("","");
    }

    private void initViewpager() {

        fragmentList.clear();

        ChargeDetailsFragment batteryDetailsFragment01 = new ChargeDetailsFragment();
        ShopDetailFragment shopDetailFragment = new ShopDetailFragment();

        fragmentList.add(batteryDetailsFragment01);
        fragmentList.add(shopDetailFragment);


        cvp_data.setAdapter(new MyPagerAdapter(getChildFragmentManager(),fragmentList));

        cvp_data.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        cvp_data.setCurrentItem(0);
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
    }
    @Override
    public void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }
    @Override
    public void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }


    @OnClick({R.id.tv_scan,R.id.tv_changeElectricity,R.id.tv_chargeElectricity,R.id.tv_show_hide,R.id.iv_shop
    ,R.id.tv_battery_detail_txt,R.id.tv_shop_detail_txt})
    public void onBtnClick(View view){
        switch (view.getId()){
            case R.id.tv_scan:
               /* if(presentShop != null){
                    Intent intent = new Intent(getActivity(), ChargeDetailActivity.class);
                    intent.putExtra("macno",presentShop.getMacno());
                    getActivity().startActivity(intent);
                }*/
                ((MainActivity)getActivity()).startScanActivity(MyConstants.REQUEST_CODE_SCAN_CHARGE);
                break;
            case R.id.tv_changeElectricity: //充电
                ((MainActivity)getActivity()).changeTag(2);
                break;
            case R.id.tv_chargeElectricity: //换电
                ((MainActivity)getActivity()).changeTag(3);
                break;
            case R.id.iv_shop:
                isShow = false;
                isShop = true;
                ll_shop.setVisibility(View.VISIBLE);
                ll_select_show.setVisibility(View.GONE);
                ll_no_select_show.setVisibility(View.GONE);
                break;
            case R.id.tv_show_hide: //换电
                if(isShow){
                    isShow = false;
                    if(isShop){
                        ll_select_show.setVisibility(View.GONE);
                    }else{
                        ll_no_select_show.setVisibility(View.GONE);
                    }
                }else{
                    isShow = true;
                    if(isShop){
                        ll_select_show.setVisibility(View.VISIBLE);
                    }else{
                        ll_no_select_show.setVisibility(View.VISIBLE);
                    }
                }
                break;
            case R.id.tv_battery_detail_txt: //
                cvp_data.setCurrentItem(0,true);
                tv_battery_detail_txt.setTextColor(getContext().getResources().getColor(R.color.blue_yq));
                tv_shop_detail_txt.setTextColor(getContext().getResources().getColor(R.color.gray_99));
                break;
            case R.id.tv_shop_detail_txt: //
                cvp_data.setCurrentItem(1,true);
                tv_battery_detail_txt.setTextColor(getContext().getResources().getColor(R.color.gray_99));
                tv_shop_detail_txt.setTextColor(getContext().getResources().getColor(R.color.blue_yq));
                break;
        }
    }

    @Override
    public void onGetShopLocationSuccess(BaseModel<ShopDetailLocationBean> data) {
        if(data != null && data.getData() != null && data.getData().getList() != null){

            listBeans =  data.getData().getList();
            for(int i = 0;i < listBeans.size();i++){
                MarkerOptions markerOption = new MarkerOptions();
                LatLng latLng = new LatLng(Double.parseDouble(listBeans.get(i).getLat()),Double.parseDouble(listBeans.get(i).getLng()));
                markerOption.position(latLng);
                markerOption.title(listBeans.get(i).getTitle()).snippet(String.valueOf(i));

                markerOption.draggable(true);//设置Marker可拖动
                markerOption.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                        .decodeResource(getResources(),R.mipmap.ic_pon)));
                // 将Marker设置为贴地显示，可以双指下拉地图查看效果
                markerOption.setFlat(true);//设置marker平贴地图效果

                aMap.addMarker(markerOption);
            }
        }


        // 定义 Marker 点击事件监听
        AMap.OnMarkerClickListener markerClickListener = new AMap.OnMarkerClickListener() {
            // marker 对象被点击时回调的接口
            // 返回 true 则表示接口已响应事件，否则返回false
            @Override
            public boolean onMarkerClick(Marker marker) {
                isShow = false;
                isShop = true;
                ll_shop.setVisibility(View.VISIBLE);
                ll_select_show.setVisibility(View.GONE);
                ll_no_select_show.setVisibility(View.GONE);

                presentShop = listBeans.get(Integer.parseInt(marker.getSnippet()));

                tv_shop_name.setText(presentShop.getTitle());
                tv_shop_address.setText(presentShop.getAddress());

                updateChildFragmentData(String.valueOf(presentShop.getAdmin_id()), presentShop.getMacno());
                return true;
            }
        };
// 绑定 Marker 被点击事件
        aMap.setOnMarkerClickListener(markerClickListener);
    }


    private void updateChildFragmentData(String shopId,String macno){
       /* if(fragmentList != null && fragmentList.size() == 2){
            ((ShopDetailFragment)fragmentList.get(1)).getShopData(shopId,macno);
            ((ChargeDetailsFragment)fragmentList.get(0)).getBatteryDetailData(macno);
        }*/

        SharedPreferencesUtils.setParam(getActivity(),"presentMacno",macno);
        SharedPreferencesUtils.setParam(getActivity(),"presentShopId",shopId);
    }

    @Override
    public void onGetDataFail() {

    }
}
