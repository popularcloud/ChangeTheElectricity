package com.younge.changetheelectricity.changetheelectricity.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.younge.changetheelectricity.R;
import com.younge.changetheelectricity.base.BaseFragment;
import com.younge.changetheelectricity.changetheelectricity.Bean.BatteryDetailsBean;
import com.younge.changetheelectricity.changetheelectricity.adapter.BatteryDetailsAdapter;
import com.younge.changetheelectricity.util.ToastUtil;

import org.byteam.superadapter.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class BatteryDetailsFragment extends BaseFragment {

    @BindView(R.id.rv_data)
    RecyclerView rv_data;
    @BindView(R.id.ll_order_battery)
    LinearLayout ll_order_battery;
    private BatteryDetailsAdapter mAdapter;


    private List<BatteryDetailsBean> allList = new ArrayList<>();

    private Unbinder unbinder;
    public BatteryDetailsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_battery_details, null);
        unbinder = ButterKnife.bind(this, v);

        initList();
        return v;
    }

    private void initList(){

        allList.clear();

        BatteryDetailsBean listBean1 = new BatteryDetailsBean();
        BatteryDetailsBean listBean2 = new BatteryDetailsBean();
        BatteryDetailsBean listBean3 = new BatteryDetailsBean();
        BatteryDetailsBean listBean4 = new BatteryDetailsBean();
        allList.add(listBean1);
        allList.add(listBean2);
        allList.add(listBean3);
        allList.add(listBean4);

        mAdapter = new BatteryDetailsAdapter(getContext(),allList,R.layout.item_battery_details);
        rv_data.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_data.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int viewType, int position) {
                ToastUtil.makeText(getContext(),"预约成功");
                ll_order_battery.setVisibility(View.VISIBLE);
                rv_data.setVisibility(View.GONE);
            }
        });
    }
}
