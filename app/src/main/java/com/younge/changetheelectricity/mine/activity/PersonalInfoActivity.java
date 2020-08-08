package com.younge.changetheelectricity.mine.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.younge.changetheelectricity.R;
import com.younge.changetheelectricity.base.BaseActivity;
import com.younge.changetheelectricity.mine.bean.UserInfoBean;
import com.younge.changetheelectricity.util.ImageLoaderUtil;
import com.younge.changetheelectricity.util.JsonUtil;
import com.younge.changetheelectricity.util.SharedPreferencesUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PersonalInfoActivity extends BaseActivity {

    @BindView(R.id.tv_center_title)
    TextView tv_center_title;

    @BindView(R.id.img_head)
    ImageView img_head;
    @BindView(R.id.txt_name)
    TextView txt_name;
    @BindView(R.id.tv_phone)
    TextView tv_phone;
    @BindView(R.id.tv_status)
    TextView tv_status;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);
        ButterKnife.bind(this);

        tv_center_title.setText("个人信息");

         String userInfoDetailStr = (String) SharedPreferencesUtils.getParam(this,"userInfoDetail","");

        UserInfoBean.UserinfoBean userInfoDetail = JsonUtil.parserGsonToObject(userInfoDetailStr, UserInfoBean.UserinfoBean.class);

        if(userInfoDetail != null){
            txt_name.setText(userInfoDetail.getUsername());
            tv_phone.setText(userInfoDetail.getMobile());
            tv_status.setText(userInfoDetail.getVerification()==1?"已认证":"未认证");
            ImageLoaderUtil.getInstance().displayFromNetDCircularT(this,userInfoDetail.getAvatar(),img_head,R.mipmap.default_portrait_100);

        }

    }

    @OnClick({R.id.rl_fanhui_left})
    public void onBtnClick(View view){
        switch (view.getId()){
            case R.id.rl_fanhui_left:
                finish();
                break;
        }
    }
}
