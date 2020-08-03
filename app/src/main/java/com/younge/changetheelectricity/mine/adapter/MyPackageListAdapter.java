package com.younge.changetheelectricity.mine.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.younge.changetheelectricity.R;
import com.younge.changetheelectricity.mine.bean.PackageBean;
import com.younge.changetheelectricity.util.DateUtil;
import com.younge.changetheelectricity.util.ImageLoaderUtil;

import org.byteam.superadapter.SuperAdapter;
import org.byteam.superadapter.SuperViewHolder;

import java.util.List;


public class MyPackageListAdapter extends SuperAdapter<PackageBean.ListBean> {

    public MyPackageListAdapter(Context context, List<PackageBean.ListBean> mDatas, int layoutId) {
       super(context,mDatas,layoutId);
    }

    @Override
    public void onBind(SuperViewHolder holder, int viewType, int layoutPosition, PackageBean.ListBean item) {


        holder.setText(R.id.tv_title,item.getTitle());
        holder.setText(R.id.tv_time, DateUtil.timeStamp2Date(String.valueOf(item.getEndtime()),"YYYY-MM-dd")+"到期");
       // holder.setText(R.id.tv_price,"￥"+item.getText().getMoney());
        switch (item.getText().getUse()){
            case "0":
                holder.setText(R.id.tv_desc,"无限次");
                break;
            default:
                holder.setText(R.id.tv_desc,item.getText().getUse()+"次");
                break;

        }

        ImageView imageView = holder.itemView.findViewById(R.id.iv_header);
        ImageLoaderUtil.getInstance().displayFromNetDCircular(mContext,item.getImage(),imageView,R.mipmap.cte_logo);

    }
}