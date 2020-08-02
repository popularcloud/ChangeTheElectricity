package com.younge.changetheelectricity.changetheelectricity.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.younge.changetheelectricity.R;
import com.younge.changetheelectricity.base.BaseFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ShopDetailFragment extends BaseFragment {

    private Unbinder unbinder;
    public ShopDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_shop_details, null);
        unbinder = ButterKnife.bind(this, v);
        return v;
    }
}
