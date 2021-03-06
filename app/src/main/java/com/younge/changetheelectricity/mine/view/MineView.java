package com.younge.changetheelectricity.mine.view;

import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.base.BaseView;
import com.younge.changetheelectricity.mine.bean.PageInfoBean;
import com.younge.changetheelectricity.mine.bean.UserInfoBean;

public interface MineView extends BaseView {

    void onGetDataSuccess(BaseModel<UserInfoBean> data);

    void onGetAboutUsSuccess(BaseModel<PageInfoBean> data);

    void onGetDataFail();
}
