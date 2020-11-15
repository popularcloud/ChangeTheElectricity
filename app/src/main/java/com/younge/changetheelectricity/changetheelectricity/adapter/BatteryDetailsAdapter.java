package com.younge.changetheelectricity.changetheelectricity.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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

        TextView tv_order = holder.findViewById(R.id.tv_order);
        TextView tv_has = holder.findViewById(R.id.tv_has);
        RelativeLayout ll_battery_bg = holder.findViewById(R.id.ll_battery_bg);
        TextView tv_has_bg = holder.findViewById(R.id.tv_has_bg);
        TextView tv_charge_time = holder.findViewById(R.id.tv_charge_time);

        holder.setText(R.id.tv_title,item.getBattery_serial());
        holder.setText(R.id.tv_has, item.getBattery()+"%");
        holder.setText(R.id.tv_num,String.valueOf(item.getDevice_box()));

        ll_battery_bg.setVisibility(View.VISIBLE);

        //计算电量背景的长度

        float bgLength =  80 * (item.getBattery()/100);

        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) tv_has_bg.getLayoutParams();
        layoutParams.width = DisplayUtil.dip2px(mContext,bgLength);


        //tv_has_bg.setWidth(DisplayUtil.dip2px(mContext,bgLength));

        tv_charge_time.setVisibility(View.GONE);
        switch (item.getStatus()){
            case 0:
                if(TextUtils.isEmpty(item.getMacno())){//空仓
                    holder.setText(R.id.tv_title,"空仓");
                    tv_order.setVisibility(View.GONE);
                    tv_has.setVisibility(View.GONE);
                    ll_battery_bg.setVisibility(View.GONE);
                }else{ //充电中

                }
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
                tv_order.setVisibility(View.VISIBLE);
                tv_has.setVisibility(View.VISIBLE);
                tv_order.setBackgroundResource(R.drawable.btn_blue_bg);
                break;
            case 2:
                tv_order.setText("已预约");
                tv_order.setVisibility(View.VISIBLE);
                tv_has.setVisibility(View.VISIBLE);
                tv_order.setBackgroundResource(R.drawable.btn_gray_bg);
                break;
            case 3:
                tv_order.setText("故\u3000障");
                tv_order.setVisibility(View.VISIBLE);
                tv_has.setVisibility(View.VISIBLE);
                tv_order.setBackgroundResource(R.drawable.btn_gray_bg);
                break;

        }

        //判断是否充电中
        if(item.getCharge_minute() != 0 && !TextUtils.isEmpty(item.getMacno())){
            tv_charge_time.setVisibility(View.VISIBLE);
            tv_charge_time.setText("约"+item.getCharge_minute()+"分钟可充满");
        }else{
            tv_charge_time.setVisibility(View.GONE);
        }


        if(mContext instanceof BatteryDetailActivity){
            tv_order.setVisibility(View.GONE);}
        tv_order.setPadding(DisplayUtil.dip2px(mContext,15),DisplayUtil.dip2px(mContext,8),DisplayUtil.dip2px(mContext,15),DisplayUtil.dip2px(mContext,8));

    }
}