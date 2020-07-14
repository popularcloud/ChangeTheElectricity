package com.younge.changetheelectricity.changetheelectricity.adapter;

import android.content.Context;

import com.younge.changetheelectricity.changetheelectricity.Bean.BatteryDetailsBean;

import org.byteam.superadapter.SuperAdapter;
import org.byteam.superadapter.SuperViewHolder;

import java.util.List;


public class ChargeDetailsAdapter extends SuperAdapter<BatteryDetailsBean> {

    public ChargeDetailsAdapter(Context context, List<BatteryDetailsBean> mDatas, int layoutId) {
       super(context,mDatas,layoutId);
    }

    @Override
    public void onBind(SuperViewHolder holder, int viewType, int layoutPosition, BatteryDetailsBean item) {

    }
}