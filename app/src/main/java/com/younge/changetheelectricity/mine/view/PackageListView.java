package com.younge.changetheelectricity.mine.view;

import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.base.BaseView;
import com.younge.changetheelectricity.mine.bean.PackageBean;

public interface PackageListView extends BaseView {


    void onGetDataSuccess(BaseModel<PackageBean> data);

    void onGetDataFail();
}
