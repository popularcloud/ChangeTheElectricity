package com.younge.changetheelectricity.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.younge.changetheelectricity.R;
import com.younge.changetheelectricity.base.BaseFragment;
import com.younge.changetheelectricity.mine.activity.BindBatteryActivity;
import com.younge.changetheelectricity.mine.activity.BindCarActivity;
import com.younge.changetheelectricity.mine.activity.MyBatteryActivity;
import com.younge.changetheelectricity.mine.activity.MyCarActivity;
import com.younge.changetheelectricity.mine.activity.MyPackageActivity;
import com.younge.changetheelectricity.mine.activity.MyWalletActivity;
import com.younge.changetheelectricity.mine.activity.PersonalInfoActivity;
import com.younge.changetheelectricity.mine.activity.RealNameAuthentication01Activity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 我的
 */
public class MineFragment extends BaseFragment {

    @BindView(R.id.imgRight)
    ImageView imgRight;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, null);
        ButterKnife.bind(this, view);
        imgRight.setVisibility(View.VISIBLE);
        imgRight.setImageResource(R.mipmap.ic_setting);
        imgRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return view;
    }

    @OnClick({R.id.ll_wallet,R.id.tv_status,R.id.tv_bind_battery,R.id.tv_bind_car,R.id.txt_name,R.id.img_head,R.id.txt_huiyuan,R.id.ll_package})
    public void onBtnClick(View view){
        switch (view.getId()){
            case R.id.ll_wallet:
                startActivity(new Intent(getActivity(), MyWalletActivity.class));
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
            case R.id.img_head:
            case R.id.txt_huiyuan:
            case R.id.txt_name:
                startActivity(new Intent(getActivity(), PersonalInfoActivity.class));
                break;
        }
    }
}
