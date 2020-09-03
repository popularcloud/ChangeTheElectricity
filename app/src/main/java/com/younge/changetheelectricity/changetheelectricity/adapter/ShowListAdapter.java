package com.younge.changetheelectricity.changetheelectricity.adapter;

import android.content.Context;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.younge.changetheelectricity.R;
import com.younge.changetheelectricity.callback.OnCommClickCallBack;

import org.byteam.superadapter.SuperAdapter;
import org.byteam.superadapter.SuperViewHolder;

import java.util.List;

public class ShowListAdapter extends SuperAdapter<String> {

    private Context context;
    private OnCommClickCallBack onCommClickCallBack;
    private int presentPosition = -1;
    public ShowListAdapter(Context context, List<String> items, int layoutResId, OnCommClickCallBack clickCallBack) {
        super(context, items, layoutResId);
        this.context = context;
        this.onCommClickCallBack = clickCallBack;
    }

    public void setItemChecked(int position){
        presentPosition = position;
        //notifyDataSetChanged();
    }
    @Override
    public void onBind(SuperViewHolder holder, int viewType, final int layoutPosition, String item) {

        TextView tv_qb_pay = holder.itemView.findViewById(R.id.tv_qb_pay);
        CheckBox cb_box = holder.itemView.findViewById(R.id.cb_box);
        tv_qb_pay.setText(item);

        cb_box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked && onCommClickCallBack != null){
                    onCommClickCallBack.onCommClick(layoutPosition);
                }
            }
        });

        if(presentPosition == layoutPosition){
            cb_box.setChecked(true);
        }else{
            cb_box.setChecked(false);
        }

    }
}
