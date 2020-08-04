package com.younge.changetheelectricity.changetheelectricity.adapter;

import android.content.Context;

import com.younge.changetheelectricity.R;
import com.younge.changetheelectricity.main.bean.DeviceDetailBean;

import org.byteam.superadapter.SuperAdapter;
import org.byteam.superadapter.SuperViewHolder;

import java.util.List;


public class ChargeDetailsTwoAdapter extends SuperAdapter<DeviceDetailBean.DeviceGoodsBean> {

    private int selPosition = 0;

    public ChargeDetailsTwoAdapter(Context context, List<DeviceDetailBean.DeviceGoodsBean> mDatas, int layoutId) {
       super(context,mDatas,layoutId);
    }

    public void setSelPosition(int selPosition) {
        this.selPosition = selPosition;
        notifyDataSetChanged();
    }

    @Override
    public void onBind(SuperViewHolder holder, int viewType, int layoutPosition, DeviceDetailBean.DeviceGoodsBean item) {

        holder.setText(R.id.tv_num,String.valueOf(item.getDevice_box()));

        if(selPosition == layoutPosition){
            holder.itemView.setBackgroundResource(R.drawable.btn_blue_bg_4dp);
            holder.setTextColor(R.id.tv_num,R.color.white);
        }else{
            holder.itemView.setBackgroundResource(R.drawable.btn_gray_bg_line);
            holder.setTextColor(R.id.tv_num,R.color.black);
        }
    }
}