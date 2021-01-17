package com.younge.changetheelectricity.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wildma.pictureselector.PictureSelector;
import com.younge.changetheelectricity.R;
import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.base.MyBaseActivity;
import com.younge.changetheelectricity.changetheelectricity.Bean.BatteryDetailsBean;
import com.younge.changetheelectricity.changetheelectricity.activity.ConfirmOrderActivity;
import com.younge.changetheelectricity.changetheelectricity.adapter.BatteryDetailsAdapter;
import com.younge.changetheelectricity.mine.bean.PackageBean;
import com.younge.changetheelectricity.mine.bean.PackageDetailBean;
import com.younge.changetheelectricity.mine.bean.ReturnImgUrlBean;
import com.younge.changetheelectricity.mine.presenter.BindCarPresenter;
import com.younge.changetheelectricity.mine.presenter.PackageDetailPresenter;
import com.younge.changetheelectricity.mine.view.BindCarView;
import com.younge.changetheelectricity.mine.view.PackageDetailView;
import com.younge.changetheelectricity.util.ImageLoaderUtil;
import com.younge.changetheelectricity.util.JsonUtil;
import com.younge.changetheelectricity.util.SharedPreferencesUtils;
import com.younge.changetheelectricity.util.ToastUtil;
import com.younge.changetheelectricity.widget.CustomDialog;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class PackageDetailActivity extends MyBaseActivity<PackageDetailPresenter> implements PackageDetailView {

    @BindView(R.id.tv_center_title)
    TextView tv_center_title;
    @BindView(R.id.tv_submit)
    TextView tv_submit;
    @BindView(R.id.tv_all_price)
    TextView tv_all_price;

    @BindView(R.id.tv_present_battery)
    TextView tv_present_battery;
    @BindView(R.id.iv_pic)
    ImageView iv_pic;
    @BindView(R.id.tv_price)
    TextView tv_price;

    @BindView(R.id.tv_desc)
    TextView tv_desc;

    @BindView(R.id.ll_reduce)
    LinearLayout ll_reduce;
    @BindView(R.id.tv_num)
    TextView tv_num;
    @BindView(R.id.iv_add)
    ImageView iv_add;

    @BindView(R.id.tv_content)
    TextView tv_content;
    private PackageDetailBean item;


    @Override
    protected PackageDetailPresenter createPresenter() {
        return new PackageDetailPresenter(this);
    }


    @Override
    protected int getContentViewId(Bundle savedInstanceState) {
        return R.layout.activity_package_detail;
    }

    @Override
    protected void init() {

        tv_center_title.setText("套餐详情");
    }

    @Override
    protected void initGetData() {

        String packageId = getIntent().getStringExtra("packageId");
        mPresenter.getPackageDtail(packageId);
    }

    @Override
    protected void widgetListener() {
        iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ll_reduce.getVisibility() == View.GONE){
                    ll_reduce.setVisibility(View.VISIBLE);
                    tv_num.setVisibility(View.VISIBLE);
                    setAllPrice(1);
                }else{
                    int goodAccount = Integer.parseInt(tv_num.getText().toString().trim());
                    goodAccount++;
                    tv_num.setText(String.valueOf(goodAccount));
                    setAllPrice(goodAccount);
                }
            }
        });

        ll_reduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int goodAccount = Integer.parseInt(tv_num.getText().toString().trim());
                goodAccount--;

                if(goodAccount == 0){
                    ll_reduce.setVisibility(View.GONE);
                    tv_num.setVisibility(View.GONE);
                }else{
                    tv_num.setText(String.valueOf(goodAccount));
                }
                setAllPrice(goodAccount);
            }

        });
    }

    @OnClick({R.id.rl_fanhui_left,R.id.tv_submit})
    public void onBtnClick(View view){
        switch (view.getId()){
            case R.id.rl_fanhui_left:
                finish();
                break;
            case R.id.tv_submit:

                List<PackageBean.ListBean> selList = new ArrayList<>();
              /*  for(int i= 0;i<mAdapter.getItemCount();i++) {
                    if(mAdapter.getItem(i).isChecked()){
                        selList.add(mAdapter.getItem(i));
                    }
                }*/

                PackageBean.ListBean listBean = new PackageBean.ListBean();
                PackageBean.ListBean.TextBean listBeanText = new PackageBean.ListBean.TextBean();
                listBeanText.setMoney(Double.valueOf(item.getText().getMoney()));

                listBean.setId(item.getId());
                listBean.setType(item.getType());
                listBean.setNum(Integer.valueOf(tv_num.getText().toString().trim()));

                listBean.setText(listBeanText);
                selList.add(listBean);

                if(selList != null && selList.size() > 0 ){
                    Intent intent = new Intent(PackageDetailActivity.this, ConfirmOrderActivity.class);
                    String jsons = JsonUtil.parserObjectToGson(selList);
                    intent.putExtra("packageDetail",jsons);
                    startActivity(intent);
                }else{
                    ToastUtil.makeText(PackageDetailActivity.this,"请选择你要购买的套餐！");
                }
                break;
        }
    }

    private void setAllPrice(int goodAccount){
        tv_all_price.setText("￥"+(goodAccount*Double.parseDouble(item.getText().getMoney())));
    }


    @Override
    public void onGetDataSuccess(BaseModel<PackageDetailBean> data) {
        if(data.getData() != null){
            item = data.getData();
            String presentSn = (String) SharedPreferencesUtils.getParam(this,"presentBattery","");
            tv_present_battery.setText("当前电池:（"+presentSn+"）");

            ImageLoaderUtil.getInstance().displayFromNetDCircular(this,item.getImage(),iv_pic,R.mipmap.cte_logo);

            tv_price.setText("￥"+item.getText().getMoney());


            StringBuilder rules = new StringBuilder();
            if(item.getType() == 1){// 1换电套餐
                switch (item.getText().getUse()){//使用次数 0无限次 1单次 其它N次
                    case "0":
                        rules.append("无限次/");
                    case "":
                        rules.append("无限次/");
                        break;
                    case "1":
                        rules.append("单次/");
                        break;
                    default:
                        rules.append(item.getText().getUse()+"次/");
                        break;
                }

                switch (item.getText().getDay()){//有效天数 0永久
                    case "0":
                        rules.append("永久");
                        break;
                    default:
                        rules.append(item.getText().getDay()+"天");
                        break;
                }

            }else if(item.getType() == 2){ //2充电套餐
                switch (item.getText().getHour()){//使用时长
                    case "0":
                        rules.append("不限制/");
                    case "":
                        rules.append("不限制/");
                        break;
                    case "1":
                        rules.append(item.getText().getHour()+"小时/");
                        break;
                    default:
                        rules.append(item.getText().getUse()+"次/");
                        break;
                }

                switch (item.getText().getDay()){//有效天数 0永久
                    case "0":
                        rules.append("永久");
                        break;
                    default:
                        rules.append(item.getText().getDay()+"天");
                        break;
                }

            }

            tv_desc.setText(rules);

            tv_content.setText(item.getRemark());
        }else{
            ToastUtil.makeText(this,"获取套餐信息失败!");
            finish();
        }
    }

    @Override
    public void onGetDataFail() {

        dissMissDialog();
    }
}
