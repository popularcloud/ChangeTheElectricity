package com.younge.changetheelectricity.changetheelectricity.adapter;

import android.content.Context;

import com.younge.changetheelectricity.R;
import com.younge.changetheelectricity.changetheelectricity.Bean.UserHistoryBean;
import com.younge.changetheelectricity.mine.bean.PackageBean;
import com.younge.changetheelectricity.util.DateUtil;

import org.byteam.superadapter.SuperAdapter;
import org.byteam.superadapter.SuperViewHolder;

import java.util.List;


public class UserHistoryAdapter extends SuperAdapter<UserHistoryBean.ListBean> {

    public UserHistoryAdapter(Context context, List<UserHistoryBean.ListBean> mDatas, int layoutId) {
       super(context,mDatas,layoutId);
    }

    @Override
    public void onBind(SuperViewHolder holder, int viewType, int layoutPosition, UserHistoryBean.ListBean item) {

        holder.setText(R.id.tv_title,item.getTitle());
        holder.setText(R.id.tv_time, DateUtil.timeStamp2Date(String.valueOf(item.getCreatetime()),"yyyy-MM-dd HH:mm:ss"));
        holder.setText(R.id.tv_shopName,item.getSeller().getNickname());
    }
}