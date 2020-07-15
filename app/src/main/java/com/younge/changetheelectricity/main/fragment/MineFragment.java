package com.younge.changetheelectricity.main.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.younge.changetheelectricity.R;
import com.younge.changetheelectricity.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 我的
 */
public class MineFragment extends BaseFragment {

    @BindView(R.id.img_head)
    ImageView img_head;
    @BindView(R.id.txt_name)
    TextView txt_name;
    @BindView(R.id.imgNewMsg)
    ImageView imgNewMsg;
    @BindView(R.id.imgRight)
    ImageView imgRight;
    @BindView(R.id.txtOrderCount)
    TextView txtOrderCount;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, null);
        ButterKnife.bind(this, view);
        imgRight.setVisibility(View.VISIBLE);
        imgRight.setImageResource(R.mipmap.ic_setting);
        imgRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return view;
    }
}
