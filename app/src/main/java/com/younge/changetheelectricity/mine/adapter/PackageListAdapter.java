package com.younge.changetheelectricity.mine.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.younge.changetheelectricity.R;
import com.younge.changetheelectricity.callback.OnItemBtnClickCallBack;
import com.younge.changetheelectricity.mine.bean.MyCarBean;
import com.younge.changetheelectricity.mine.bean.PackageBean;
import com.younge.changetheelectricity.util.ImageLoaderUtil;

import org.byteam.superadapter.SuperAdapter;
import org.byteam.superadapter.SuperViewHolder;

import java.util.List;


public class PackageListAdapter extends SuperAdapter<PackageBean.ListBean> {


    private OnItemBtnClickCallBack onItemBtnClickCallBack;

    public PackageListAdapter(Context context, List<PackageBean.ListBean> mDatas, int layoutId, OnItemBtnClickCallBack onItemBtnClickCallBack) {
       super(context,mDatas,layoutId);
       this.onItemBtnClickCallBack = onItemBtnClickCallBack;
    }

    @Override
    public void onBind(SuperViewHolder holder, int viewType, int layoutPosition, PackageBean.ListBean item) {


        holder.setText(R.id.tv_title,item.getTitle());
        holder.setText(R.id.tv_price,"￥"+item.getText().getMoney());

        LinearLayout ll_reduce = holder.findViewById(R.id.ll_reduce);
        TextView tv_num = holder.findViewById(R.id.tv_num);
        ImageView iv_add = holder.findViewById(R.id.iv_add);

        iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ll_reduce.getVisibility() == View.GONE){
                    ll_reduce.setVisibility(View.VISIBLE);
                    tv_num.setVisibility(View.VISIBLE);
                    item.setChecked(true);
                    item.setNum(1);
                }else{
                    int goodAccount = Integer.parseInt(tv_num.getText().toString().trim());
                    goodAccount++;
                    tv_num.setText(String.valueOf(goodAccount));
                    item.setChecked(true);
                    item.setNum(goodAccount);
                }

                onItemBtnClickCallBack.OnItemBtnclick(layoutPosition,0);
            }
        });

        ll_reduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    int goodAccount = Integer.parseInt(tv_num.getText().toString().trim());
                    goodAccount--;

                    if(goodAccount == 0){
                        ll_reduce.setVisibility(View.GONE);
                        tv_num.setVisibility(View.GONE);
                        item.setChecked(false);
                        item.setNum(0);
                    }else{
                        item.setChecked(true);
                        item.setNum(goodAccount);
                        tv_num.setText(String.valueOf(goodAccount));
                    }
                onItemBtnClickCallBack.OnItemBtnclick(layoutPosition,0);
            }
        });

        StringBuilder rules = new StringBuilder();

        if(item.getType() == 1){// 1换电套餐
            switch (item.getText().getUse()){//使用次数 0无限次 1单次 其它N次
                case "0":
                    rules.append("无限次/");
                case "":
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

        }else if(item.getType() == 2){ //2充电套餐
            switch (item.getText().getHour()){//使用时长
                case "0":
                    rules.append("不限制/");
                case "":
                    rules.append("不限制/");
                    break;
                case "1":
                    rules.append(item.getText().getHour()+"小时/");
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

        }


        holder.setText(R.id.tv_desc,rules);

        ImageView imageView = holder.itemView.findViewById(R.id.iv_header);
        ImageLoaderUtil.getInstance().displayFromNetDCircular(mContext,item.getImage(),imageView,R.mipmap.cte_logo);

    }
}