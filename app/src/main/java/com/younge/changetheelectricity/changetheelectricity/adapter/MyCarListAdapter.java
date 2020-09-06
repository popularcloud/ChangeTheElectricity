package com.younge.changetheelectricity.changetheelectricity.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;

import com.younge.changetheelectricity.R;
import com.younge.changetheelectricity.callback.OnItemBtnClickCallBack;
import com.younge.changetheelectricity.changetheelectricity.Bean.BatteryDetailsBean;
import com.younge.changetheelectricity.mine.bean.MyCarBean;
import com.younge.changetheelectricity.util.ImageLoaderUtil;

import org.byteam.superadapter.SuperAdapter;
import org.byteam.superadapter.SuperViewHolder;

import java.util.List;


public class MyCarListAdapter extends SuperAdapter<MyCarBean.ListBean> {


    private OnItemBtnClickCallBack onItemBtnClickCallBack;

    public MyCarListAdapter(Context context, List<MyCarBean.ListBean> mDatas, int layoutId, OnItemBtnClickCallBack onItemBtnClickCallBack) {
       super(context,mDatas,layoutId);
       this.onItemBtnClickCallBack = onItemBtnClickCallBack;
    }

    @Override
    public void onBind(SuperViewHolder holder, int viewType, int layoutPosition, MyCarBean.ListBean item) {


        holder.setText(R.id.tv_id,item.getCarvin());
        holder.setText(R.id.tv_battery_id, TextUtils.isEmpty(item.getBattery_sn())?"未绑定电池":String.valueOf(item.getBattery_sn()));
        holder.setText(R.id.tv_car_brand,item.getSerial());
        holder.setText(R.id.tv_car_num,item.getCarno());

        ImageView imageView = holder.itemView.findViewById(R.id.tv_car_header);
        ImageLoaderUtil.getInstance().displayFromNetDCircular(mContext,item.getPicfront(),imageView,R.mipmap.cte_logo);


        ImageView iv_more = holder.itemView.findViewById(R.id.iv_more);

        iv_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建弹出式菜单对象（最低版本11）
                PopupMenu popup = new PopupMenu(mContext, v);//第二个参数是绑定的那个view
                //获取菜单填充器
                MenuInflater inflater = popup.getMenuInflater();
                //填充菜单
                inflater.inflate(R.menu.popupmenu_two, popup.getMenu());
                //绑定菜单项的点击事件
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if(onItemBtnClickCallBack != null){
                            if(item.getItemId() == R.id.item_bind_battery){
                                onItemBtnClickCallBack.OnItemBtnclick(layoutPosition,0);
                            }else{
                                onItemBtnClickCallBack.OnItemBtnclick(layoutPosition,1);
                            }

                        }
                        return false;
                    }
                });
                //显示(这一行代码不要忘记了)
                popup.show();
            }
        });

    }
}