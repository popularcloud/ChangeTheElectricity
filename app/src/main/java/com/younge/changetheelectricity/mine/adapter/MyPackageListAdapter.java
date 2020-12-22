package com.younge.changetheelectricity.mine.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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
        holder.setText(R.id.tv_time, DateUtil.timeStamp2Date(String.valueOf(item.getEndtime()),"yyyy-MM-dd")+"到期");
       // holder.setText(R.id.tv_price,"￥"+item.getText().getMoney());

        TextView tv_no_use = holder.itemView.findViewById(R.id.tv_no_use);
        switch (item.getNum()){
            case 0:
                holder.setText(R.id.tv_desc,"无限次");
                break;
            default:
               holder.setText(R.id.tv_desc,"剩余 "+(item.getNum()-item.getUse())+"次");
                break;

        }

        if(layoutPosition == 0){
            tv_no_use.setVisibility(View.VISIBLE);
        }else{
            tv_no_use.setVisibility(View.GONE);
        }

        ImageView imageView = holder.itemView.findViewById(R.id.tv_header);
        ImageLoaderUtil.getInstance().displayFromNetDCircular(mContext,item.getImage(),imageView,R.mipmap.cte_logo);

    }
}