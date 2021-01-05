package com.younge.changetheelectricity.changetheelectricity.adapter;

import android.content.Context;

import com.younge.changetheelectricity.R;
import com.younge.changetheelectricity.changetheelectricity.Bean.BatteryDetailsBean;
import com.younge.changetheelectricity.mine.bean.PackageBean;

import org.byteam.superadapter.SuperAdapter;
import org.byteam.superadapter.SuperViewHolder;

import java.util.List;


public class ChargeDetailsAdapter extends SuperAdapter<PackageBean.ListBean> {

    private int selPosition = -1;

    public ChargeDetailsAdapter(Context context, List<PackageBean.ListBean> mDatas, int layoutId) {
       super(context,mDatas,layoutId);
    }

    public void setSelPosition(int selPosition) {
        this.selPosition = selPosition;
        notifyDataSetChanged();
    }

    @Override
    public void onBind(SuperViewHolder holder, int viewType, int layoutPosition, PackageBean.ListBean item) {

        holder.setText(R.id.tv_hour,item.getText().getHour()+"小时");
        holder.setText(R.id.tv_money,"¥"+item.getText().getMoney());

        if(selPosition == layoutPosition){
            holder.itemView.setBackgroundResource(R.drawable.btn_blue_bg_4dp);
            holder.setTextColor(R.id.tv_money,R.color.white);
            holder.setTextColor(R.id.tv_hour,R.color.white);
        }else{
            holder.itemView.setBackgroundResource(R.drawable.btn_gray_bg_line);
            holder.setTextColor(R.id.tv_money,R.color.black);
            holder.setTextColor(R.id.tv_hour,R.color.black);
        }
    }
}