package com.younge.changetheelectricity.changetheelectricity.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.younge.changetheelectricity.R;
import com.younge.changetheelectricity.changetheelectricity.Bean.BatteryDetailsBean;
import com.younge.changetheelectricity.mine.bean.MyCarBean;
import com.younge.changetheelectricity.util.ImageLoaderUtil;

import org.byteam.superadapter.SuperAdapter;
import org.byteam.superadapter.SuperViewHolder;

import java.util.List;


public class MyCarListAdapter extends SuperAdapter<MyCarBean.ListBean> {

    public MyCarListAdapter(Context context, List<MyCarBean.ListBean> mDatas, int layoutId) {
       super(context,mDatas,layoutId);
    }

    @Override
    public void onBind(SuperViewHolder holder, int viewType, int layoutPosition, MyCarBean.ListBean item) {


        holder.setText(R.id.tv_id,item.getCarvin());
        holder.setText(R.id.tv_battery_id,item.getBattery_id()==0?"未绑定":String.valueOf(item.getBattery_id()));
        holder.setText(R.id.tv_car_brand,item.getSerial());
        holder.setText(R.id.tv_car_num,item.getCarno());

        ImageView imageView = holder.itemView.findViewById(R.id.tv_car_header);
        ImageLoaderUtil.getInstance().displayFromNetDCircular(mContext,item.getPicfront(),imageView,R.mipmap.cte_logo);

    }
}