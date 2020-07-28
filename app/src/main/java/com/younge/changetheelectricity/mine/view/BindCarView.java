package com.younge.changetheelectricity.mine.view;

import com.google.gson.JsonObject;
import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.base.BaseView;
import com.younge.changetheelectricity.mine.bean.ReturnImgUrlBean;

public interface BindCarView extends BaseView {


    void onUploadPicSuccess(BaseModel<ReturnImgUrlBean> data);

    void onAddCarSuccess(BaseModel<Object> data);

    void onGetDataFail();
}
