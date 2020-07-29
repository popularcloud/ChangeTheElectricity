package com.younge.changetheelectricity.mine.view;

import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.base.BaseView;
import com.younge.changetheelectricity.mine.bean.MyCarBean;

public interface MyCarView extends BaseView {


    void onGetCarSuccess(BaseModel<MyCarBean> data);

    void onGetDataFail();
}
