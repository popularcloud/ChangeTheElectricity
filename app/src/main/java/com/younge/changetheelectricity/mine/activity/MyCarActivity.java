package com.younge.changetheelectricity.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.younge.changetheelectricity.R;
import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.base.MyBaseActivity;
import com.younge.changetheelectricity.callback.OnItemBtnClickCallBack;
import com.younge.changetheelectricity.changetheelectricity.adapter.MyCarListAdapter;
import com.younge.changetheelectricity.mine.bean.BaseItemBean;
import com.younge.changetheelectricity.mine.bean.MyBatteryBean;
import com.younge.changetheelectricity.mine.bean.MyCarBean;
import com.younge.changetheelectricity.mine.presenter.MyCarPresenter;
import com.younge.changetheelectricity.mine.view.MyCarView;
import com.younge.changetheelectricity.util.SharedPreferencesUtils;
import com.younge.changetheelectricity.util.ToastUtil;
import com.younge.changetheelectricity.widget.CustomDialog;
import com.younge.changetheelectricity.widget.ShowListDialog;

import org.byteam.superadapter.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

public class MyCarActivity extends MyBaseActivity<MyCarPresenter> implements MyCarView {


    @BindView(R.id.tv_center_title)
    TextView tv_center_title;
    @BindView(R.id.tv_right)
    TextView tv_right;

    @BindView(R.id.rv_data)
    RecyclerView rv_data;
    @BindView(R.id.mBGARefreshLayout)
    BGARefreshLayout mBGARefreshLayout;

    private MyCarListAdapter mAdapter;

    String presentOperateId;

    private List<MyCarBean.ListBean> allList = new ArrayList<>();
    private int page = 1;

    private ShowListDialog showListDialog;


    @Override
    protected MyCarPresenter createPresenter() {
        return new MyCarPresenter(this);
    }

    @Override
    protected int getContentViewId(Bundle savedInstanceState) {
        return R.layout.activity_my_car;
    }

    @Override
    protected void init() {

        tv_center_title.setText("我的车辆");
        tv_right.setVisibility(View.VISIBLE);
        tv_right.setText("添加车辆");
        tv_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyCarActivity.this, BindCarActivity.class));
            }
        });
        initList();
    }

    @Override
    protected void initGetData() {

    }

    @Override
    protected void widgetListener() {

    }

    private void initList(){

        BGANormalRefreshViewHolder refreshViewHolder = new BGANormalRefreshViewHolder(this, false);
        mBGARefreshLayout.setRefreshViewHolder(refreshViewHolder);

        mAdapter = new MyCarListAdapter(this, allList, R.layout.item_my_car, new OnItemBtnClickCallBack() {
            @Override
            public void OnItemBtnclick(int pisition, int btn) {
                if(btn == 1){ //删除
                    mPresenter.delCar(String.valueOf(mAdapter.getItem(pisition).getId()), (String) SharedPreferencesUtils.getParam(MyCarActivity.this,"token",""));
                }else if(btn == 0){ //改绑电池
                    presentOperateId = String.valueOf(mAdapter.getItem(pisition).getId());
                    mPresenter.getMyBattery("1",(String) SharedPreferencesUtils.getParam(MyCarActivity.this,"token",""));
                }else if(btn == 2){ //使用车辆
                    presentOperateId = String.valueOf(mAdapter.getItem(pisition).getId());
                    mPresenter.car_default(presentOperateId,(String) SharedPreferencesUtils.getParam(MyCarActivity.this,"token",""));
                }
            }
        });
        rv_data.setLayoutManager(new LinearLayoutManager(this));
        rv_data.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int viewType, int position) {

            }
        });


        mBGARefreshLayout.setDelegate(new BGARefreshLayout.BGARefreshLayoutDelegate() {
            @Override
            public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
                page = 1;
                mPresenter.getMyCarList(String.valueOf(page), String.valueOf(SharedPreferencesUtils.getParam(MyCarActivity.this,"token","")));
            }

            @Override
            public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
                page++;
                mPresenter.getMyCarList(String.valueOf(page), String.valueOf(SharedPreferencesUtils.getParam(MyCarActivity.this,"token","")));
                return true;
            }
        });

        mBGARefreshLayout.beginRefreshing();


    }

    @OnClick({R.id.rl_fanhui_left})
    public void onBtnClick(View view){
        switch (view.getId()){
            case R.id.rl_fanhui_left:
                finish();
                break;
        }
    }

    @Override
    public void onGetCarSuccess(BaseModel<MyCarBean> data) {

        if(data != null && data.getData() != null && data.getData().getList() != null && data.getData().getList().size() > 0){
            mBGARefreshLayout.setVisibility(View.VISIBLE);
            if(page == 1) {
                mAdapter.replaceAll(data.getData().getList());
            }else{
                mAdapter.addAll(data.getData().getList());
            }
        }else{
            mBGARefreshLayout.setVisibility(View.INVISIBLE);
        }


        if(page == 1) {
           mBGARefreshLayout.endRefreshing();
        }else{
            mBGARefreshLayout.endLoadingMore();
        }
    }

    @Override
    public void onGetBatterySuccess(BaseModel<MyBatteryBean> data) {

        if(data != null && data.getData() != null && data.getData().getList() != null && data.getData().getList().size() > 0){

            List<MyBatteryBean.ListBean> dataList = data.getData().getList();
            List<BaseItemBean> myReason = new ArrayList<BaseItemBean>();

            for(int i = 0; i < dataList.size(); i++){
                myReason.add(new BaseItemBean(String.valueOf(dataList.get(i).getId()),dataList.get(i).getSn()));
            }

            showListDialog = new ShowListDialog(MyCarActivity.this, new ShowListDialog.CallBack() {
                @Override
                public void onSubmit(int position) {
                    showListDialog.dismiss();
                    if(presentOperateId != null){
                        mPresenter.carBindBattery(myReason.get(position).getId(),presentOperateId, (String) SharedPreferencesUtils.getParam(MyCarActivity.this,"token",""));
                    }
                }
            },myReason,"改绑电池","",false);
            showListDialog.show();

        }else{
            ToastUtil.makeText(this,"您还未添加电池！");
        }


    }

    @Override
    public void onCarBatteryBindSuccess(BaseModel data) {
        ToastUtil.makeText(this,"绑定成功！");
        mBGARefreshLayout.beginRefreshing();

    }

    @Override
    public void onSetCarDefault(BaseModel data) {
        ToastUtil.makeText(this,"使用成功！");
        mBGARefreshLayout.beginRefreshing();
    }

    @Override
    public void onDelSuccess(BaseModel data) {
        ToastUtil.makeText(this,"删除成功！");
        mBGARefreshLayout.beginRefreshing();
    }

    @Override
    public void onGetDataFail() {
        if(page == 1) {
            mBGARefreshLayout.endRefreshing();
        }else{
            mBGARefreshLayout.endLoadingMore();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(mBGARefreshLayout != null){
            mBGARefreshLayout.beginRefreshing();
        }
    }
}
