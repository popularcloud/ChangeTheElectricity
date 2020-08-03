package com.younge.changetheelectricity.changetheelectricity.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.younge.changetheelectricity.R;
import com.younge.changetheelectricity.changetheelectricity.activity.BatteryDetailActivity;
import com.younge.changetheelectricity.main.bean.DeviceDetailBean;

import org.byteam.superadapter.SuperAdapter;
import org.byteam.superadapter.SuperViewHolder;

import java.util.List;


public class MainChargeDetailsAdapter extends SuperAdapter<DeviceDetailBean.DeviceGoodsBean> {

    public MainChargeDetailsAdapter(Context context, List<DeviceDetailBean.DeviceGoodsBean> mDatas, int layoutId) {
       super(context,mDatas,layoutId);
    }

    @Override
    public void onBind(SuperViewHolder holder, int viewType, int layoutPosition, DeviceDetailBean.DeviceGoodsBean item) {


        holder.setText(R.id.tv_num,""+(layoutPosition+1));


        switch (item.getStatus()){//,//0无电池或充电中 1可使用 2已预约 3故障
            case 0:
                holder.setText(R.id.tv_status,"充电中");
                break;
            case 1:
                holder.setText(R.id.tv_status,"可使用");
                break;
            case 2:
                holder.setText(R.id.tv_status,"已预约");
                break;
            case 3:
                holder.setText(R.id.tv_status,"故障");
                break;


        }

    }
}