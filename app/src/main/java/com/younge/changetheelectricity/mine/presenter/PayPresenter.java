package com.younge.changetheelectricity.mine.presenter;

import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.base.BaseObserver;
import com.younge.changetheelectricity.base.BasePresenter;
import com.younge.changetheelectricity.mine.bean.ReturnImgUrlBean;
import com.younge.changetheelectricity.mine.view.BindCarView;
import com.younge.changetheelectricity.mine.view.PayView;
import com.younge.changetheelectricity.net.ApiRetrofit;

import java.util.List;

import okhttp3.MultipartBody;

public class PayPresenter extends BasePresenter<PayView> {

    public PayPresenter(PayView baseView) {
        super(baseView);
    }


}
