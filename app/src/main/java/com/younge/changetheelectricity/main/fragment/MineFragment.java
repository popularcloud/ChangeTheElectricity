package com.younge.changetheelectricity.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.younge.changetheelectricity.R;
import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.base.MyBaseFragment;
import com.younge.changetheelectricity.mine.activity.MyBatteryActivity;
import com.younge.changetheelectricity.mine.activity.MyCarActivity;
import com.younge.changetheelectricity.mine.activity.MyPackageActivity;
import com.younge.changetheelectricity.mine.activity.MyWalletActivity;
import com.younge.changetheelectricity.mine.activity.PersonalInfoActivity;
import com.younge.changetheelectricity.mine.activity.RealNameAuthentication01Activity;
import com.younge.changetheelectricity.mine.activity.SettingActivity;
import com.younge.changetheelectricity.mine.activity.ShareSettingActivity;
import com.younge.changetheelectricity.mine.bean.PageInfoBean;
import com.younge.changetheelectricity.mine.bean.UserInfoBean;
import com.younge.changetheelectricity.mine.presenter.MinePresenter;
import com.younge.changetheelectricity.mine.view.MineView;
import com.younge.changetheelectricity.util.ImageLoaderUtil;
import com.younge.changetheelectricity.util.JsonUtil;
import com.younge.changetheelectricity.util.SharedPreferencesUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 我的
 */
public class MineFragment extends MyBaseFragment<MinePresenter> implements MineView {

    @BindView(R.id.imgRight)
    ImageView imgRight;

    @BindView(R.id.txt_name)
    TextView txt_name;
    @BindView(R.id.tv_status)
    TextView tv_status;
    @BindView(R.id.img_head)
    ImageView img_head;


    private String token;

    private UserInfoBean.UserinfoBean userinfoBean;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, null);
        ButterKnife.bind(this, view);
        imgRight.setVisibility(View.VISIBLE);
        imgRight.setImageResource(R.mipmap.ic_setting);
        imgRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SettingActivity.class));
            }
        });

        createPresenter();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        token = (String) SharedPreferencesUtils.getParam(getContext(),"token","");
        if(mPresenter != null && !TextUtils.isEmpty(token)){
            mPresenter.getPersonalInfo(token);
        }
    }

    @OnClick({R.id.ll_wallet,R.id.tv_status,R.id.tv_bind_battery,R.id.tv_bind_car,R.id.txt_name,R.id.img_head,R.id.txt_huiyuan,R.id.ll_package,R.id.ll_invitation})
    public void onBtnClick(View view){
        switch (view.getId()){
            case R.id.ll_wallet:
                Intent intent = new Intent(getActivity(), MyWalletActivity.class);
                intent.putExtra("money",userinfoBean.getMoney());
                startActivity(intent);
                break;
            case R.id.tv_status:
                startActivity(new Intent(getActivity(), RealNameAuthentication01Activity.class));
                break;
            case R.id.tv_bind_battery:
                startActivity(new Intent(getActivity(), MyBatteryActivity.class));
                break;
            case R.id.tv_bind_car:
                startActivity(new Intent(getActivity(), MyCarActivity.class));
                break;
            case R.id.ll_package:
                startActivity(new Intent(getActivity(), MyPackageActivity.class));
                break;
            case R.id.ll_invitation:
                startActivity(new Intent(getActivity(), ShareSettingActivity.class));
                break;
            case R.id.img_head:
            case R.id.txt_huiyuan:
            case R.id.txt_name:
                startActivity(new Intent(getActivity(), PersonalInfoActivity.class));
                break;
        }
    }

    @Override
    protected MinePresenter createPresenter() {
        mPresenter = new MinePresenter(this);
        return mPresenter;
    }

    @Override
    public void onGetDataSuccess(BaseModel<UserInfoBean> data) {
        userinfoBean = data.getData().getUserinfo();

        String userInfoDetail = JsonUtil.parserObjectToGson(userinfoBean);
        SharedPreferencesUtils.setParam(getContext(),"userInfoDetail",userInfoDetail);

        if(!TextUtils.isEmpty(userinfoBean.getUsername())){
            txt_name.setText(userinfoBean.getUsername());
        }else{
            txt_name.setText(userinfoBean.getNickname());
        }


        ImageLoaderUtil.getInstance().displayFromNetDCircularT(getContext(),userinfoBean.getAvatar(),img_head,R.mipmap.default_portrait_100);



       // tv_status.setText(userinfoBean.getVerification_desc());

    }

    @Override
    public void onGetAboutUsSuccess(BaseModel<PageInfoBean> data) {

    }

    @Override
    public void onGetDataFail() {

    }
}
