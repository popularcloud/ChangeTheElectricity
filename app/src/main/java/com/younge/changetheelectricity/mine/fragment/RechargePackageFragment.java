package com.younge.changetheelectricity.mine.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.younge.changetheelectricity.R;
import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.base.MyBaseFragment;
import com.younge.changetheelectricity.callback.OnItemBtnClickCallBack;
import com.younge.changetheelectricity.changetheelectricity.activity.ConfirmOrderActivity;
import com.younge.changetheelectricity.mine.adapter.PackageListAdapter;
import com.younge.changetheelectricity.mine.bean.PackageBean;
import com.younge.changetheelectricity.mine.presenter.PackagePresenter;
import com.younge.changetheelectricity.mine.view.PackageListView;
import com.younge.changetheelectricity.util.JsonUtil;
import com.younge.changetheelectricity.util.SharedPreferencesUtils;
import com.younge.changetheelectricity.util.ToastUtil;

import org.byteam.superadapter.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

public class RechargePackageFragment extends MyBaseFragment<PackagePresenter> implements PackageListView {


    @BindView(R.id.rv_data)
    RecyclerView rv_data;
    @BindView(R.id.mBGARefreshLayout)
    BGARefreshLayout mBGARefreshLayout;

    @BindView(R.id.tv_submit)
    TextView tv_submit;
    @BindView(R.id.tv_price)
    TextView tv_price;
    @BindView(R.id.tv_present_battery)
    TextView tv_present_battery;

    private PackageListAdapter mAdapter;


    private List<PackageBean.ListBean> allList = new ArrayList<>();

    private int page = 1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recharge_package, null);
        ButterKnife.bind(this, view);

        mPresenter = createPresenter();

        String presentSn = (String) SharedPreferencesUtils.getParam(getContext(),"presentBattery","");
        tv_present_battery.setText("当前电池:（"+presentSn+"）");

        initList();
        return view;
    }


    private void initList(){

        BGANormalRefreshViewHolder refreshViewHolder = new BGANormalRefreshViewHolder(getActivity(), false);
        mBGARefreshLayout.setRefreshViewHolder(refreshViewHolder);

        mAdapter = new PackageListAdapter(getActivity(), allList, R.layout.item_package_list, new OnItemBtnClickCallBack() {
            @Override
            public void OnItemBtnclick(int pisition, int btn) {

                Double allShowPrice = 0d;
                for(int i= 0;i<mAdapter.getItemCount();i++) {
                    if(mAdapter.getItem(i).isChecked()){
                        allShowPrice = allShowPrice + mAdapter.getItem(i).getNum() * mAdapter.getItem(i).getText().getMoney();
                    }
                }

                tv_price.setText("¥ "+allShowPrice);
            }
        });
        rv_data.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_data.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int viewType, int position) {
             /*   Intent intent = new Intent(getActivity(), ConfirmOrderActivity.class);
                intent.putExtra("packageDetail",mAdapter.getItem(position));
                startActivity(intent);*/
            }
        });

        mBGARefreshLayout.setDelegate(new BGARefreshLayout.BGARefreshLayoutDelegate() {
            @Override
            public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
                page = 1;
                mPresenter.getPackageList("2",String.valueOf(page));
            }

            @Override
            public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
                page++;
                mPresenter.getPackageList("2",String.valueOf(page));
                return true;
            }
        });
        mBGARefreshLayout.beginRefreshing();

        tv_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<PackageBean.ListBean> selList = new ArrayList<>();
                for(int i= 0;i<mAdapter.getItemCount();i++) {
                    if(mAdapter.getItem(i).isChecked()){
                        selList.add(mAdapter.getItem(i));
                    }
                }

                if(selList != null && selList.size() > 0 ){
                    Intent intent = new Intent(getActivity(), ConfirmOrderActivity.class);
                    String jsons = JsonUtil.parserObjectToGson(selList);
                    intent.putExtra("packageDetail",jsons);
                    startActivity(intent);
                }else{
                    ToastUtil.makeText(getContext(),"请选择你要购买的套餐！");
                }

            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    protected PackagePresenter createPresenter() {
        return new PackagePresenter(this);
    }

    @Override
    public void onGetDataSuccess(BaseModel<PackageBean> data) {
        if(data != null && data.getData() != null && data.getData().getList() != null){
            if(page == 1) {
                mAdapter.replaceAll(data.getData().getList());
            }else{
                mAdapter.addAll(data.getData().getList());
            }
        }


        if(page == 1) {
            mBGARefreshLayout.endRefreshing();
        }else{
            mBGARefreshLayout.endLoadingMore();
        }
    }

    @Override
    public void onGetDataFail() {
        if(page == 1) {
            mBGARefreshLayout.endRefreshing();
        }else{
            mBGARefreshLayout.endLoadingMore();
        }
    }
}
