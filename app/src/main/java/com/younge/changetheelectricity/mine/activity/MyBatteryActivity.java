package com.younge.changetheelectricity.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.younge.changetheelectricity.R;
import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.base.MyBaseActivity;
import com.younge.changetheelectricity.base.MyConstants;
import com.younge.changetheelectricity.callback.OnItemBtnClickCallBack;
import com.younge.changetheelectricity.changetheelectricity.adapter.MyBatteryListAdapter;
import com.younge.changetheelectricity.mine.bean.MyBatteryBean;
import com.younge.changetheelectricity.mine.bean.MyCarBean;
import com.younge.changetheelectricity.mine.presenter.MyBatteryPresenter;
import com.younge.changetheelectricity.mine.view.MyBatteryView;
import com.younge.changetheelectricity.util.SharedPreferencesUtils;
import com.younge.changetheelectricity.util.ToastUtil;
import com.younge.changetheelectricity.widget.ShowListDialog;
import com.yzq.zxinglibrary.android.CaptureActivity;
import com.yzq.zxinglibrary.common.Constant;

import org.byteam.superadapter.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

public class MyBatteryActivity extends MyBaseActivity<MyBatteryPresenter> implements MyBatteryView {

    @BindView(R.id.rv_data)
    RecyclerView rv_data;
    @BindView(R.id.tv_center_title)
    TextView tv_center_title;
    @BindView(R.id.tv_right)
    TextView tv_right;
    @BindView(R.id.mBGARefreshLayout)
    BGARefreshLayout mBGARefreshLayout;

    private MyBatteryListAdapter mAdapter;

    private List<MyBatteryBean.ListBean> allList = new ArrayList<>();

    int page = 1;

    private ShowListDialog showListDialog;
    private String presentOperateId;

    @Override
    protected MyBatteryPresenter createPresenter() {
        return new MyBatteryPresenter(this);
    }

    @Override
    protected int getContentViewId(Bundle savedInstanceState) {
        return R.layout.activity_my_battery;
    }

    @Override
    protected void init() {
        tv_center_title.setText("我的电池");

        tv_right.setVisibility(View.VISIBLE);
        tv_right.setText("添加电池");
        tv_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // startActivity(new Intent(MyBatteryActivity.this, BatterySNActivity.class));

                Intent intent = new Intent(MyBatteryActivity.this, CaptureActivity.class);
                startActivityForResult(intent, MyConstants.REQUEST_CODE_BIND_BATTERY);
            }
        });
        initList();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mBGARefreshLayout != null) {
            mBGARefreshLayout.beginRefreshing();
        }
    }

    @Override
    protected void initGetData() {

    }

    @Override
    protected void widgetListener() {

    }

    private void initList() {

        allList.clear();

        BGANormalRefreshViewHolder refreshViewHolder = new BGANormalRefreshViewHolder(this, false);
        mBGARefreshLayout.setRefreshViewHolder(refreshViewHolder);

        mAdapter = new MyBatteryListAdapter(this, allList, R.layout.item_my_battery, new OnItemBtnClickCallBack() {
            @Override
            public void OnItemBtnclick(int pisition, int btn) {

                if(btn == 1){ //删除
                    mPresenter.delBattery(String.valueOf(mAdapter.getItem(pisition).getId()), (String) SharedPreferencesUtils.getParam(MyBatteryActivity.this,"token",""));
                }else{
                    presentOperateId = String.valueOf(mAdapter.getItem(pisition).getId());
                    mPresenter.getMyCarList("1",(String) SharedPreferencesUtils.getParam(MyBatteryActivity.this,"token",""));
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
                mPresenter.getMyBattery(String.valueOf(page), String.valueOf(SharedPreferencesUtils.getParam(MyBatteryActivity.this, "token", "")));
            }

            @Override
            public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
                page++;
                mPresenter.getMyBattery(String.valueOf(page), String.valueOf(SharedPreferencesUtils.getParam(MyBatteryActivity.this, "token", "")));
                return true;
            }
        });

    }

    @OnClick({R.id.rl_fanhui_left})
    public void onBtnClick(View view) {
        switch (view.getId()) {
            case R.id.rl_fanhui_left:
                finish();
                break;
        }
    }

    @Override
    public void onGetBatterySuccess(BaseModel<MyBatteryBean> data) {
        if (data != null && data.getData() != null && data.getData().getList() != null && data.getData().getList().size() > 0) {
            mBGARefreshLayout.setVisibility(View.VISIBLE);
            SharedPreferencesUtils.setParam(MyBatteryActivity.this,"presentBattery",data.getData().getList().get(0).getSn());
            if (page == 1) {
                mAdapter.replaceAll(data.getData().getList());
            } else {
                mAdapter.addAll(data.getData().getList());
            }
        }else{
            mBGARefreshLayout.setVisibility(View.INVISIBLE);
        }


        if (page == 1) {
            mBGARefreshLayout.endRefreshing();
        } else {
            mBGARefreshLayout.endLoadingMore();
        }
    }

    @Override
    public void onGetCarSuccess(BaseModel<MyCarBean> data) {
        if(data != null && data.getData() != null && data.getData().getList() != null && data.getData().getList().size() > 0){

            List<MyCarBean.ListBean> dataList = data.getData().getList();
            List<String> myReason = new ArrayList<>();

            for(int i = 0; i < dataList.size(); i++){
                myReason.add(String.valueOf(dataList.get(i).getId()));
            }

            showListDialog = new ShowListDialog(MyBatteryActivity.this, new ShowListDialog.CallBack() {
                @Override
                public void onSubmit(int position) {
                    showListDialog.dismiss();
                    if(presentOperateId != null){
                        mPresenter.carBindBattery(myReason.get(position),presentOperateId, (String) SharedPreferencesUtils.getParam(MyBatteryActivity.this,"token",""));
                    }
                }
            },myReason,"改绑电池","",false);
            showListDialog.show();

        }else{
            ToastUtil.makeText(this,"您还未绑定电池！");
        }
    }

    @Override
    public void onCarBatteryBindSuccess(BaseModel data) {
        ToastUtil.makeText(this,"绑定成功！");
        mBGARefreshLayout.beginRefreshing();
    }

    @Override
    public void onDelSuccess(BaseModel data) {
        ToastUtil.makeText(this,"删除成功！");
        mBGARefreshLayout.beginRefreshing();
    }

    @Override
    public void onGetDataFail() {
        if (page == 1) {
            mBGARefreshLayout.endRefreshing();
        } else {
            mBGARefreshLayout.endLoadingMore();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            // 扫描二维码/条码回传
            if (requestCode == MyConstants.REQUEST_CODE_BIND_BATTERY) {
                if (data != null) {

                    String content = data.getStringExtra(Constant.CODED_CONTENT);
                    // result.setText("扫描结果为：" + content);
                    // Toast.makeText(this, "解析结果:" + content, Toast.LENGTH_LONG).show();
                    Log.e("msg", "解析结果:" + content);

                    int indexStr = content.indexOf("sn=");

                    String sn = content.substring(indexStr + 3);

                    Log.e("msg", "sn:" + sn);

                    if (!TextUtils.isEmpty(sn)) {
                        Intent intent = new Intent(MyBatteryActivity.this,BindBatteryActivity.class);
                        intent.putExtra("sn",sn);
                        startActivity(intent);
                    }
                }
            }
        }
    }

}
