package com.younge.changetheelectricity.main;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.younge.changetheelectricity.R;
import com.younge.changetheelectricity.base.BaseActivity;
import com.younge.changetheelectricity.base.MyConstants;
import com.younge.changetheelectricity.changetheelectricity.activity.BatteryDetailActivity;
import com.younge.changetheelectricity.changetheelectricity.activity.ChargeDetailActivity;
import com.younge.changetheelectricity.main.adapter.FragmentsPagerAdapter;
import com.younge.changetheelectricity.main.fragment.HuodongFragment;
import com.younge.changetheelectricity.main.fragment.MainChargeFragment;
import com.younge.changetheelectricity.main.fragment.MainFragment;
import com.younge.changetheelectricity.main.fragment.MineFragment;
import com.younge.changetheelectricity.main.fragment.ServiceFragment;
import com.younge.changetheelectricity.main.fragment.ShopFragment;
import com.younge.changetheelectricity.widget.CustomViewPager;
import com.yzq.zxinglibrary.android.CaptureActivity;
import com.yzq.zxinglibrary.common.Constant;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.cViewPager)
    CustomViewPager cViewPager;
    @BindView(R.id.radio_home)
    RadioButton radioHome;
    @BindView(R.id.radio_mba)
    RadioButton radioMBA;
    @BindView(R.id.radio_order)
    RadioButton radioOrder;
    @BindView(R.id.radio_news)
    RadioButton radioNews;
    @BindView(R.id.radio_mine)
    RadioButton radioMine;
    @BindView(R.id.radio_charge_battery)
    RadioButton radio_charge_battery;
    @BindView(R.id.group_tab)
    RadioGroup groupTab;


    /**
     * fragment相关
     */
    private HashMap<Integer, Fragment> fragmentHashMap;
    private HashMap rButtonHashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initFragment();
    }

    private void initFragment(){

        fragmentHashMap = new HashMap<>();
       /* fragmentHashMap.put(0, new ServiceFragment());
        fragmentHashMap.put(1, new ShopFragment());
        fragmentHashMap.put(2, new MainFragment());
        fragmentHashMap.put(3, new MainChargeFragment());
        fragmentHashMap.put(4, new HuodongFragment());
        fragmentHashMap.put(5,new MineFragment());

        rButtonHashMap = new HashMap<>();
        rButtonHashMap.put(0, radioHome);
        rButtonHashMap.put(1, radioMBA);
        rButtonHashMap.put(2, radioNews);
        rButtonHashMap.put(3, radio_charge_battery);
        rButtonHashMap.put(4, radioOrder);
        rButtonHashMap.put(5, radioMine);*/

        fragmentHashMap.put(0, new MainFragment());
        fragmentHashMap.put(1, new MainChargeFragment());
        fragmentHashMap.put(2,new MineFragment());

        rButtonHashMap = new HashMap<>();
        rButtonHashMap.put(0, radioNews);
        rButtonHashMap.put(1, radio_charge_battery);
        rButtonHashMap.put(2, radioMine);

        //是否滑动
        cViewPager.setPagingEnabled(false);
        cViewPager.setOffscreenPageLimit(3);
        cViewPager.setAdapter(new FragmentsPagerAdapter(getSupportFragmentManager(), fragmentHashMap));
        cViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if(position == 3){
                    cViewPager.setChecked(rButtonHashMap, 2);
                }else{
                    cViewPager.setChecked(rButtonHashMap, position);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    public void changeTag(int position){
        cViewPager.setChecked(rButtonHashMap, position);
        cViewPager.setCurrentItem(position, true);
    }

    @OnClick({R.id.radio_home, R.id.radio_order, R.id.radio_news, R.id.radio_mine,R.id.radio_mba})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.radio_news:
                cViewPager.setCurrentItem(0, false);
                break;
            case R.id.radio_mine:
                cViewPager.setCurrentItem(2, false);
                break;
        }
    }

    public void startScanActivity(int reqeustCode){
        Intent intent = new Intent(this, CaptureActivity.class);
        startActivityForResult(intent,reqeustCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){

            // 扫描二维码/条码回传
            if (requestCode == MyConstants.REQUEST_CODE_SCAN_CHANGE) {
                if (data != null) {

                    String content = data.getStringExtra(Constant.CODED_CONTENT);
                    // result.setText("扫描结果为：" + content);
                   // Toast.makeText(this, "解析结果:" + content, Toast.LENGTH_LONG).show();
                    Log.e("msg","解析结果:" + content);

                    int indexStr = content.indexOf("macno=");

                    String macno = content.substring(indexStr+6);

                    Log.e("msg","macno:" + macno);

                    if(!TextUtils.isEmpty(macno)){
                        Intent intent = new Intent(MainActivity.this, BatteryDetailActivity.class);
                        intent.putExtra("macno",macno);
                        startActivity(intent);
                    }
                }
            }if (requestCode == MyConstants.REQUEST_CODE_SCAN_CHARGE) {
                if (data != null) {

                    String content = data.getStringExtra(Constant.CODED_CONTENT);
                    // result.setText("扫描结果为：" + content);
                   // Toast.makeText(this, "解析结果:" + content, Toast.LENGTH_LONG).show();
                    Log.e("msg","解析结果:" + content);

                    int indexStr = content.indexOf("macno=");

                    String macno = content.substring(indexStr+6);

                    Log.e("msg","macno:" + macno);

                    if(!TextUtils.isEmpty(macno)){
                        Intent intent = new Intent(MainActivity.this, ChargeDetailActivity.class);
                        intent.putExtra("macno",macno);
                        startActivity(intent);
                    }
                }
            }
        }
    }
}
