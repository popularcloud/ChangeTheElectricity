package com.younge.changetheelectricity.changetheelectricity.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.younge.changetheelectricity.R;
import com.younge.changetheelectricity.changetheelectricity.Bean.BatteryDetailsBean;
import com.younge.changetheelectricity.changetheelectricity.activity.BatteryDetailActivity;
import com.younge.changetheelectricity.main.bean.DeviceDetailBean;


import org.byteam.superadapter.SuperAdapter;
import org.byteam.superadapter.SuperViewHolder;

import java.util.List;


public class BatteryDetailsAdapter extends SuperAdapter<DeviceDetailBean.DeviceGoodsBean> {

    public BatteryDetailsAdapter(Context context, List<DeviceDetailBean.DeviceGoodsBean> mDatas, int layoutId) {
       super(context,mDatas,layoutId);
    }

    @Override
    public void onBind(SuperViewHolder holder, int viewType, int layoutPosition, DeviceDetailBean.DeviceGoodsBean item) {


        holder.setText(R.id.tv_title,item.getVoltage()+"V  "+ item.getVolume()+"Ah");
        holder.setText(R.id.tv_has,item.getBattery()+"%");

       TextView tv_order = holder.findViewById(R.id.tv_order);

        if(mContext instanceof BatteryDetailActivity){
            tv_order.setVisibility(View.GONE);
        }

    /*   tv_order.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

           }
       });*/

    }
}