package com.younge.changetheelectricity.changetheelectricity.adapter;

import android.content.Context;

import com.younge.changetheelectricity.R;
import com.younge.changetheelectricity.changetheelectricity.Bean.BatteryDetailsBean;

import org.byteam.superadapter.SuperAdapter;
import org.byteam.superadapter.SuperViewHolder;

import java.util.List;


public class ChargeDetailsAdapter extends SuperAdapter<BatteryDetailsBean> {

    private int selPosition = 0;

    public ChargeDetailsAdapter(Context context, List<BatteryDetailsBean> mDatas, int layoutId) {
       super(context,mDatas,layoutId);
    }

    public void setSelPosition(int selPosition) {
        this.selPosition = selPosition;
        notifyDataSetChanged();
    }

    @Override
    public void onBind(SuperViewHolder holder, int viewType, int layoutPosition, BatteryDetailsBean item) {

        if(selPosition == layoutPosition){
            holder.itemView.setBackgroundResource(R.drawable.btn_blue_bg_4dp);
        }else{
            holder.itemView.setBackgroundResource(R.drawable.btn_gray_bg_line);
        }
    }
}