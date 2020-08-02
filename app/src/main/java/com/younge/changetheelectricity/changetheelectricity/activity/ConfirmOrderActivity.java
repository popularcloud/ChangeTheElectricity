package com.younge.changetheelectricity.changetheelectricity.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.younge.changetheelectricity.R;
import com.younge.changetheelectricity.base.BaseActivity;
import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.base.MyBaseActivity;
import com.younge.changetheelectricity.changetheelectricity.Bean.BatteryDetailsBean;
import com.younge.changetheelectricity.changetheelectricity.adapter.ConfirmOrderAdapter;
import com.younge.changetheelectricity.mine.bean.PackageBean;
import com.younge.changetheelectricity.mine.presenter.PackagePresenter;
import com.younge.changetheelectricity.mine.presenter.PayPresenter;
import com.younge.changetheelectricity.mine.view.PayView;

import org.byteam.superadapter.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConfirmOrderActivity extends MyBaseActivity<PayPresenter> implements PayView {

    @BindView(R.id.rv_data)
    RecyclerView rv_data;
    @BindView(R.id.tv_center_title)
    TextView tv_center_title;
    @BindView(R.id.tv_submit)
    TextView tv_submit;
    private ConfirmOrderAdapter mAdapter;

    private List<PackageBean.ListBean> allList = new ArrayList<>();

    @Override
    protected PayPresenter createPresenter() {
        return new PayPresenter(this);
    }

    @Override
    protected int getContentViewId(Bundle savedInstanceState) {
        return R.layout.activity_confirm_order;
    }

    @Override
    protected void init() {
        tv_center_title.setText("确认订单");

        PackageBean.ListBean packageDetail = (PackageBean.ListBean) getIntent().getSerializableExtra("packageDetail");

        initList(packageDetail);
    }

    @Override
    protected void initGetData() {

    }

    @Override
    protected void widgetListener() {

    }

    private void initList(PackageBean.ListBean packageDetail){

        allList.clear();

        allList.add(packageDetail);

        mAdapter = new ConfirmOrderAdapter(this,allList,R.layout.item_comfirm_order);
        rv_data.setLayoutManager(new LinearLayoutManager(this));
        rv_data.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int viewType, int position) {

            }
        });
    }

    @OnClick({R.id.tv_submit,R.id.rl_fanhui_left})
    public void onBtnClick(View view){
        switch (view.getId()){
            case R.id.tv_submit:
               // startActivity(new Intent(this, ConfirmOrderActivity.class));
                break;
            case R.id.rl_fanhui_left:
                finish();
                break;
        }
    }

    @Override
    public void onGetPaySuccess(BaseModel<Object> data) {

    }

    @Override
    public void onGetDataFail() {

    }
}
