package com.younge.changetheelectricity.mine.view;

import com.google.gson.JsonObject;
import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.base.BaseView;

public interface BindCarView extends BaseView {


    void onUploadPicSuccess(BaseModel<JsonObject> data);

    void onAddCarSuccess(BaseModel<JsonObject> data);

    void onGetDataFail();
}
