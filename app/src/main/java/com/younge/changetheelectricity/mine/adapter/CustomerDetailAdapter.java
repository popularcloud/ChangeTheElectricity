package com.younge.changetheelectricity.mine.adapter;

import android.content.Context;

import com.younge.changetheelectricity.R;
import com.younge.changetheelectricity.mine.bean.DepositHistoryBean;
import com.younge.changetheelectricity.util.DateUtil;

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
        holder.setText(R.id.tv_money,"￥"+item.getMoney());
        holder.setText(R.id.tv_time, DateUtil.timeStamp2Date(String.valueOf(item.getCreatetime()),"yyyy-MM-dd HH:mm:ss"));


        //alipay支付宝 wechat微信 money余额
        switch (item.getPaytype()){
            case "alipay":
                holder.setText(R.id.tv_payType,"支付宝支付");
                break;
            case "wechat":
                holder.setText(R.id.tv_payType,"微信支付");
                break;
            case "money":
                holder.setText(R.id.tv_payType,"余额支付");
                break;
        }


       /* ImageView imageView = holder.itemView.findViewById(R.id.iv_header);
        ImageLoaderUtil.getInstance().displayFromNetDCircular(mContext,item.getPicfront(),imageView,R.mipmap.cte_logo);*/

    }
}