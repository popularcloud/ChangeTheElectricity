package com.younge.changetheelectricity.mine.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.younge.changetheelectricity.R;
import com.younge.changetheelectricity.mine.bean.PackageBean;
import com.younge.changetheelectricity.util.callback.OnSelectEventMessage;

import org.byteam.superadapter.SuperAdapter;
import org.byteam.superadapter.SuperViewHolder;
import org.greenrobot.eventbus.EventBus;

import java.util.List;


public class RechargePackageAdapter extends SuperAdapter<PackageBean.ListBean> {

    private TextView lastSelect;
    private int lastPosition;

    public RechargePackageAdapter(Context context, List<PackageBean.ListBean> mDatas, int layoutId) {
       super(context,mDatas,layoutId);
    }

    @Override
    public void onBind(SuperViewHolder holder, int viewType, int layoutPosition,PackageBean.ListBean item) {

        TextView tv_title = holder.itemView.findViewById(R.id.tv_title);
        tv_title.setText(item.getText().getMoney()+"元");

        if(item.isChecked()){
            tv_title.setBackgroundResource(R.drawable.btn_blue_bg_8px);
            tv_title.setTextColor(mContext.getResources().getColor(R.color.white));
            lastSelect = tv_title;
            lastPosition = layoutPosition;
        }else{
            tv_title.setBackgroundResource(R.drawable.btn_gray_99_line_8px);
            tv_title.setTextColor(mContext.getResources().getColor(R.color.black_33));
        }

        tv_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView thisText = (TextView) v;
                thisText.setBackgroundResource(R.drawable.btn_blue_bg_8px);
                thisText.setTextColor(mContext.getResources().getColor(R.color.white));
                item.setChecked(true);

                if(lastSelect != null){
                    lastSelect.setBackgroundResource(R.drawable.btn_gray_99_line_8px);
                    lastSelect.setTextColor(mContext.getResources().getColor(R.color.black_33));
                    getItem(lastPosition).setChecked(false);
                }

                lastSelect = thisText;
                lastPosition = layoutPosition;

                OnSelectEventMessage onSelectEventMessage = new OnSelectEventMessage(layoutPosition);
                EventBus.getDefault().post(onSelectEventMessage);

            }
        });
    }
}
