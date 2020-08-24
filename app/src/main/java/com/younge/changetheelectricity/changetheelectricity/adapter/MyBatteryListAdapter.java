package com.younge.changetheelectricity.changetheelectricity.adapter;

import android.content.Context;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;

import com.younge.changetheelectricity.R;
import com.younge.changetheelectricity.callback.OnItemBtnClickCallBack;
import com.younge.changetheelectricity.mine.bean.MyBatteryBean;
import com.younge.changetheelectricity.mine.bean.MyCarBean;
import com.younge.changetheelectricity.util.ImageLoaderUtil;

import org.byteam.superadapter.SuperAdapter;
import org.byteam.superadapter.SuperViewHolder;

import java.util.List;


public class MyBatteryListAdapter extends SuperAdapter<MyBatteryBean.ListBean> {

    private OnItemBtnClickCallBack onItemBtnClickCallBack;

    public MyBatteryListAdapter(Context context, List<MyBatteryBean.ListBean> mDatas, int layoutId, OnItemBtnClickCallBack onItemBtnClickCallBack) {
       super(context,mDatas,layoutId);
       this.onItemBtnClickCallBack = onItemBtnClickCallBack;
    }

    @Override
    public void onBind(SuperViewHolder holder, int viewType, int layoutPosition, MyBatteryBean.ListBean item) {


        holder.setText(R.id.tv_battery_id,item.getNo());
        holder.setText(R.id.tv_car,item.getCar_carvin()==null?"未绑定车辆":String.valueOf(item.getCar_carvin()));
        holder.setText(R.id.tv_type,item.getSerial());
        holder.setText(R.id.tv_sn,item.getSn());
        holder.setText(R.id.tv_quantity,item.getBattery()+"%");

        ImageView imageView = holder.itemView.findViewById(R.id.iv_more);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建弹出式菜单对象（最低版本11）
                PopupMenu popup = new PopupMenu(mContext, v);//第二个参数是绑定的那个view
                //获取菜单填充器
                MenuInflater inflater = popup.getMenuInflater();
                //填充菜单
                inflater.inflate(R.menu.popupmenu, popup.getMenu());
                //绑定菜单项的点击事件
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if(onItemBtnClickCallBack != null){
                            if(item.getItemId() == R.id.item_bind_car){
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