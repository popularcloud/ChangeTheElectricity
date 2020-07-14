package com.younge.changetheelectricity.main.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.younge.changetheelectricity.R;
import com.younge.changetheelectricity.base.BaseFragment;

import butterknife.ButterKnife;

public class ServiceFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_service, null);
        ButterKnife.bind(this, view);
        return view;
    }
}
