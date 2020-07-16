package com.younge.changetheelectricity.main.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.younge.changetheelectricity.R;
import com.younge.changetheelectricity.base.BaseFragment;

import butterknife.ButterKnife;

/**
 * 我的
 */
public class ShopFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop, null);
        ButterKnife.bind(this, view);
        return view;
    }
}
