package com.younge.changetheelectricity.mine.view;

import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.base.BaseView;
import com.younge.changetheelectricity.mine.bean.PackageBean;
import com.younge.changetheelectricity.mine.bean.PackageDetailBean;

public interface PackageDetailView extends BaseView {


    void onGetDataSuccess(BaseModel<PackageDetailBean> data);

    void onGetDataFail();
}
