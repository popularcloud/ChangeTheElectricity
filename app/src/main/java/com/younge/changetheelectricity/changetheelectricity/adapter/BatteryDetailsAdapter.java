package com.younge.changetheelectricity.changetheelectricity.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.younge.changetheelectricity.R;
import com.younge.changetheelectricity.callback.OnItemBtnClickCallBack;
import com.younge.changetheelectricity.changetheelectricity.Bean.BatteryDetailsBean;
import com.younge.changetheelectricity.changetheelectricity.activity.BatteryDetailActivity;
import com.younge.changetheelectricity.main.bean.DeviceDetailBean;
import com.younge.changetheelectricity.util.DisplayUtil;


import org.byteam.superadapter.OnItemClickListener;
import org.byteam.superadapter.SuperAdapter;
import org.byteam.superadapter.SuperViewHolder;

import java.util.List;




public class BatteryDetailsAdapter extends SuperAdapter<DeviceDetailBean.DeviceGoodsBean> {

    private OnItemBtnClickCallBack onItemBtnClickCallBack;
    public BatteryDetailsAdapter(Context context, List<DeviceDetailBean.DeviceGoodsBean> mDatas, int layoutId, OnItemBtnClickCallBack onItemBtnClickCallBack) {
       super(context,mDatas,layoutId);
       this.onItemBtnClickCallBack = onItemBtnClickCallBack;
    }

    @Override
    public void onBind(SuperViewHolder holder, int viewType, int layoutPosition, DeviceDetailBean.DeviceGoodsBean item) {


        holder.setText(R.id.tv_title,item.getVoltage()+"V  "+ item.getVolume()+"Ah");
      //  holder.setText(R.id.tv_has,item.getBattery().getBattery()+"%");
        holder.setText(R.id.tv_num,String.valueOf(item.getDevice_box()));

       TextView tv_order = holder.findViewById(R.id.tv_order);

        if(mContext instanceof BatteryDetailActivity){
            tv_order.setVisibility(View.GONE);
           }


        switch (item.getStatus()){
            case 0:
                tv_order.setText("无电池");
                tv_order.setBackgroundResource(R.drawable.btn_blue_bg);
                break;
            case 1:
                tv_order.setText("预\u3000约");
                tv_order.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(onItemBtnClickCallBack != null){
                            onItemBtnClickCallBack.OnItemBtnclick(layoutPosition,0);
                        }

                    }
                });
                tv_order.setBackgroundResource(R.drawable.btn_blue_bg);
                break;
            case 2:
                tv_order.setText("已预约");
                tv_order.setBackgroundResource(R.drawable.btn_blue_bg);
                break;
            case 3:
                tv_order.setText("故\u3000障");
                tv_order.setBackgroundResource(R.drawable.btn_blue_bg);
                break;

        }

        tv_order.setPadding(DisplayUtil.dip2px(mContext,15),DisplayUtil.dip2px(mContext,8),DisplayUtil.dip2px(mContext,15),DisplayUtil.dip2px(mContext,8));

    }
}