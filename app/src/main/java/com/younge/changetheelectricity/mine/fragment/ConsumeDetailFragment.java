package com.younge.changetheelectricity.mine.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.younge.changetheelectricity.R;
import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.base.MyBaseFragment;
import com.younge.changetheelectricity.mine.adapter.CustomerDetailAdapter;
import com.younge.changetheelectricity.mine.bean.DepositHistoryBean;
import com.younge.changetheelectricity.mine.presenter.DepositHistoryPresenter;
import com.younge.changetheelectricity.mine.view.DepositHistoryView;
import com.younge.changetheelectricity.util.SharedPreferencesUtils;

import org.byteam.superadapter.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

public class ConsumeDetailFragment extends MyBaseFragment<DepositHistoryPresenter> implements DepositHistoryView {


    @BindView(R.id.rv_data)
    RecyclerView rv_data;
    @BindView(R.id.mBGARefreshLayout)
    BGARefreshLayout mBGARefreshLayout;

    private CustomerDetailAdapter mAdapter;


    private List<DepositHistoryBean.ListBean> allList = new ArrayList<>();

    private int page = 1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer_detail, null);
        ButterKnife.bind(this, view);

        mPresenter = createPresenter();

        initList();
        return view;
    }


    private void initList(){

        BGANormalRefreshViewHolder refreshViewHolder = new BGANormalRefreshViewHolder(getActivity(), false);
        mBGARefreshLayout.setRefreshViewHolder(refreshViewHolder);

        mAdapter = new CustomerDetailAdapter(getActivity(),allList,R.layout.item_deposit_history);
        rv_data.setLayoutManager(new LinearLayoutManager(getActivity()));
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
                mPresenter.getBatteryInfo("7",String.valueOf(page),"10", (String) SharedPreferencesUtils.getParam(getContext(),"token",""));
            }

            @Override
            public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
                page++;
                mPresenter.getBatteryInfo("7",String.valueOf(page),"10", (String) SharedPreferencesUtils.getParam(getContext(),"token",""));
                return true;
            }
        });
        mBGARefreshLayout.beginRefreshing();
    }


    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    protected DepositHistoryPresenter createPresenter() {
        return new DepositHistoryPresenter(this);
    }

    @Override
    public void onGetDepositHistorySuccess(BaseModel<DepositHistoryBean> data) {
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
