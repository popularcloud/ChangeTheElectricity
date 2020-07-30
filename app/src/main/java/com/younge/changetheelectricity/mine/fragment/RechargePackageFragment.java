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
import com.younge.changetheelectricity.mine.adapter.PackageListAdapter;
import com.younge.changetheelectricity.mine.bean.PackageBean;
import com.younge.changetheelectricity.mine.presenter.PackagePresenter;
import com.younge.changetheelectricity.mine.view.PackageListView;

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

    private PackageListAdapter mAdapter;


    private List<PackageBean.ListBean> allList = new ArrayList<>();

    private int page = 1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recharge_package, null);
        ButterKnife.bind(this, view);

        mPresenter = createPresenter();

        initList();
        return view;
    }


    private void initList(){

        BGANormalRefreshViewHolder refreshViewHolder = new BGANormalRefreshViewHolder(getActivity(), false);
        mBGARefreshLayout.setRefreshViewHolder(refreshViewHolder);

        mAdapter = new PackageListAdapter(getActivity(),allList,R.layout.item_package_list);
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
                mPresenter.getPackageList("0",String.valueOf(page));
            }

            @Override
            public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
                page++;
                mPresenter.getPackageList("0",String.valueOf(page));
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
