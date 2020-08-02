package com.younge.changetheelectricity.mine.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.younge.changetheelectricity.R;
import com.younge.changetheelectricity.mine.bean.MyCarBean;
import com.younge.changetheelectricity.mine.bean.PackageBean;
import com.younge.changetheelectricity.util.ImageLoaderUtil;

import org.byteam.superadapter.SuperAdapter;
import org.byteam.superadapter.SuperViewHolder;

import java.util.List;


public class PackageListAdapter extends SuperAdapter<PackageBean.ListBean> {

    public PackageListAdapter(Context context, List<PackageBean.ListBean> mDatas, int layoutId) {
       super(context,mDatas,layoutId);
    }

    @Override
    public void onBind(SuperViewHolder holder, int viewType, int layoutPosition, PackageBean.ListBean item) {


        holder.setText(R.id.tv_title,item.getTitle());
        holder.setText(R.id.tv_price,"￥"+item.getText().getMoney());
        holder.setText(R.id.tv_desc,item.getText().getUse()+"次/"+item.getText().getDay()+"天");

        ImageView imageView = holder.itemView.findViewById(R.id.iv_header);
        ImageLoaderUtil.getInstance().displayFromNetDCircular(mContext,item.getImage(),imageView,R.mipmap.cte_logo);

    }
}