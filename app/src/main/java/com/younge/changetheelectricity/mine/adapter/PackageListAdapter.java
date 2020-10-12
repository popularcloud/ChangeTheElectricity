package com.younge.changetheelectricity.mine.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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

        TextView tv_reduce = holder.findViewById(R.id.tv_reduce);
        TextView tv_num = holder.findViewById(R.id.tv_num);
        TextView iv_add = holder.findViewById(R.id.iv_add);

        iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        StringBuilder rules = new StringBuilder();

        switch (item.getText().getUse()){//使用次数 0无限次 1单次 其它N次
            case "0":
                rules.append("无限次/");
                break;
            case "1":
                rules.append("单次/");
                break;
            default:
                rules.append(item.getText().getUse()+"次/");
                break;
        }

        switch (item.getText().getDay()){//有效天数 0永久
            case "0":
                rules.append("永久");
                break;
            default:
                rules.append(item.getText().getDay()+"天");
                break;
        }

        holder.setText(R.id.tv_desc,rules);

        ImageView imageView = holder.itemView.findViewById(R.id.iv_header);
        ImageLoaderUtil.getInstance().displayFromNetDCircular(mContext,item.getImage(),imageView,R.mipmap.cte_logo);

    }
}