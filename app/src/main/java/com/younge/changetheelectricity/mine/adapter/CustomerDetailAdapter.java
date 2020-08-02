package com.younge.changetheelectricity.mine.adapter;

import android.content.Context;

import com.younge.changetheelectricity.R;
import com.younge.changetheelectricity.mine.bean.DepositHistoryBean;
import com.younge.changetheelectricity.mine.bean.PackageBean;

import org.byteam.superadapter.SuperAdapter;
import org.byteam.superadapter.SuperViewHolder;

import java.util.List;


public class CustomerDetailAdapter extends SuperAdapter<DepositHistoryBean.ListBean> {

    public CustomerDetailAdapter(Context context, List<DepositHistoryBean.ListBean> mDatas, int layoutId) {
       super(context,mDatas,layoutId);
    }

    @Override
    public void onBind(SuperViewHolder holder, int viewType, int layoutPosition, DepositHistoryBean.ListBean item) {


        holder.setText(R.id.tv_title,item.getMemo());
        holder.setText(R.id.tv_price,"￥"+item.getMoney());
        holder.setText(R.id.tv_time,String.valueOf(item.getCreatetime()));
        holder.setText(R.id.tv_payType,item.getPaytype());

       /* ImageView imageView = holder.itemView.findViewById(R.id.iv_header);
        ImageLoaderUtil.getInstance().displayFromNetDCircular(mContext,item.getPicfront(),imageView,R.mipmap.cte_logo);*/

    }
}