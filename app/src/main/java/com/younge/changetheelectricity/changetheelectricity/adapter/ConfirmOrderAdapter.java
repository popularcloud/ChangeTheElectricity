package com.younge.changetheelectricity.changetheelectricity.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.younge.changetheelectricity.R;
import com.younge.changetheelectricity.changetheelectricity.Bean.BatteryDetailsBean;
import com.younge.changetheelectricity.mine.bean.PackageBean;
import com.younge.changetheelectricity.util.ImageLoaderUtil;

import org.byteam.superadapter.SuperAdapter;
import org.byteam.superadapter.SuperViewHolder;

import java.util.List;


public class ConfirmOrderAdapter extends SuperAdapter<PackageBean.ListBean> {

    public ConfirmOrderAdapter(Context context, List<PackageBean.ListBean> mDatas, int layoutId) {
       super(context,mDatas,layoutId);
    }

    @Override
    public void onBind(SuperViewHolder holder, int viewType, int layoutPosition, PackageBean.ListBean item) {

        holder.setText(R.id.tv_title,item.getTitle());
        holder.setText(R.id.tv_money,"￥"+item.getText().getMoney());
        holder.setText(R.id.tv_sum,"x "+item.getNum());

        ImageView imageView = holder.itemView.findViewById(R.id.iv_header);
        ImageLoaderUtil.getInstance().displayFromNetDCircular(mContext,item.getImage(),imageView,R.mipmap.cte_logo);
    }
}