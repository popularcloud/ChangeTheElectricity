package com.younge.changetheelectricity.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.wildma.pictureselector.PictureSelector;
import com.younge.changetheelectricity.R;
import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.base.MyBaseActivity;
import com.younge.changetheelectricity.changetheelectricity.Bean.BatteryDetailsBean;
import com.younge.changetheelectricity.changetheelectricity.adapter.BatteryDetailsAdapter;
import com.younge.changetheelectricity.mine.bean.ReturnImgUrlBean;
import com.younge.changetheelectricity.mine.presenter.BindCarPresenter;
import com.younge.changetheelectricity.mine.view.BindCarView;
import com.younge.changetheelectricity.util.ImageLoaderUtil;
import com.younge.changetheelectricity.util.JsonUtil;
import com.younge.changetheelectricity.util.SharedPreferencesUtils;
import com.younge.changetheelectricity.util.ToastUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class BindCarActivity extends MyBaseActivity<BindCarPresenter> implements BindCarView {

    @BindView(R.id.tv_center_title)
    TextView tv_center_title;
    @BindView(R.id.tv_submit)
    TextView tv_submit;

    @BindView(R.id.et_car_frame_number)
    EditText et_car_frame_number;
    @BindView(R.id.et_car_name)
    EditText et_car_name;
    @BindView(R.id.et_car_number)
    EditText et_car_number;

    @BindView(R.id.iv_car_positive)
    ImageView iv_car_positive;
    @BindView(R.id.iv_car_back)
    ImageView iv_car_back;
    @BindView(R.id.iv_car_left)
    ImageView iv_car_left;
    @BindView(R.id.iv_car_right)
    ImageView iv_car_right;

    String positiveImg;
    String backImg;
    String leftImg;
    String rightImg;

    int imgType = 0;

    private BatteryDetailsAdapter mAdapter;

    private List<BatteryDetailsBean> allList = new ArrayList<>();

    @Override
    protected BindCarPresenter createPresenter() {
        return new BindCarPresenter(this);
    }


    @Override
    protected int getContentViewId(Bundle savedInstanceState) {
        return R.layout.activity_bind_car;
    }

    @Override
    protected void init() {

        tv_center_title.setText("绑定车辆");
    }

    @Override
    protected void initGetData() {

    }

    @Override
    protected void widgetListener() {

    }

    @OnClick({R.id.rl_fanhui_left,R.id.tv_submit,R.id.iv_car_back,R.id.iv_car_left,R.id.iv_car_right,R.id.iv_car_positive})
    public void onBtnClick(View view){
        switch (view.getId()){
            case R.id.rl_fanhui_left:
                finish();
                break;
            case R.id.tv_submit:

                String carvin = et_car_frame_number.getText().toString().trim();
                String serial = et_car_name.getText().toString().trim();
                String carno = et_car_number.getText().toString().trim();

                if(TextUtils.isEmpty(carvin)){
                    ToastUtil.makeText(BindCarActivity.this,"请填写车架编号");
                    return;
                }

                if(TextUtils.isEmpty(serial)){
                    ToastUtil.makeText(BindCarActivity.this,"请填写车辆品名");
                    return;
                }

                if(TextUtils.isEmpty(carno)){
                    ToastUtil.makeText(BindCarActivity.this,"请填写车辆牌号");
                    return;
                }

                if(TextUtils.isEmpty(positiveImg)){
                    ToastUtil.makeText(BindCarActivity.this,"请选择车辆正面照片");
                    return;
                }


                if(TextUtils.isEmpty(backImg)){
                    ToastUtil.makeText(BindCarActivity.this,"请选择车辆背面照片");
                    return;
                }


                if(TextUtils.isEmpty(leftImg)){
                    ToastUtil.makeText(BindCarActivity.this,"请选择车辆左侧照片");
                    return;
                }


                if(TextUtils.isEmpty(rightImg)){
                    ToastUtil.makeText(BindCarActivity.this,"请选择车辆右侧照片");
                    return;
                }

                mPresenter.addCar(carvin,serial,carno,positiveImg,backImg,leftImg,rightImg, String.valueOf(SharedPreferencesUtils.getParam(BindCarActivity.this,"token","")));
                //ToastUtil.makeText(this,"绑定成功！");
                //startActivity(new Intent(BindCarActivity.this, MyCarActivity.class));
                break;
            case R.id.iv_car_positive:
                imgType = 0;
                /**
                 * create()方法参数一是上下文，在activity中传activity.this，在fragment中传fragment.this。参数二为请求码，用于结果回调onActivityResult中判断
                 * selectPicture()方法参数分别为 是否裁剪、裁剪后图片的宽(单位px)、裁剪后图片的高、宽比例、高比例。都不传则默认为裁剪，宽200，高200，宽高比例为1：1。
                 */
               PictureSelector
                        .create(BindCarActivity.this, PictureSelector.SELECT_REQUEST_CODE)
                        .selectPicture(true, 200, 200, 1, 1);
                break;
            case R.id.iv_car_back:
                imgType = 1;
                PictureSelector
                        .create(BindCarActivity.this, PictureSelector.SELECT_REQUEST_CODE)
                        .selectPicture(true, 200, 200, 1, 1);
                break;
            case R.id.iv_car_left:
                imgType = 2;
                PictureSelector
                        .create(BindCarActivity.this, PictureSelector.SELECT_REQUEST_CODE)
                        .selectPicture(true, 200, 200, 1, 1);
                break;
            case R.id.iv_car_right:
                imgType = 3;
                PictureSelector
                        .create(BindCarActivity.this, PictureSelector.SELECT_REQUEST_CODE)
                        .selectPicture(true, 200, 200, 1, 1);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /*结果回调*/
        if (requestCode == PictureSelector.SELECT_REQUEST_CODE) {
            if (data != null) {
                String picturePath = data.getStringExtra(PictureSelector.PICTURE_PATH);

                Log.d("picAddress",picturePath);
                File file = new File(picturePath);

                MultipartBody.Builder builder = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)//表单类型
                        .addFormDataPart("HTTP_API", "api/common/upload");
                RequestBody photoRequestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                builder.addFormDataPart("file", file.getName(), photoRequestBody);

                showLoadingDialog("图片上传中...");

                mPresenter.uploadPicToService(builder.build().parts());
            }
        }
    }


    @Override
    public void onUploadPicSuccess(BaseModel<ReturnImgUrlBean> data) {

        dissMissDialog();

        switch (imgType){
            case 0:
                positiveImg = data.getData().getUrl();
                ImageLoaderUtil.getInstance().displayFromNetDCircular(BindCarActivity.this,positiveImg,iv_car_positive,R.mipmap.cte_logo);
                break;
            case 1:
                backImg = data.getData().getUrl();
                ImageLoaderUtil.getInstance().displayFromNetDCircular(BindCarActivity.this,backImg,iv_car_back,R.mipmap.cte_logo);
                break;
            case 2:
                leftImg = data.getData().getUrl();
                ImageLoaderUtil.getInstance().displayFromNetDCircular(BindCarActivity.this,leftImg,iv_car_left,R.mipmap.cte_logo);
                break;
            case 3:
                rightImg = data.getData().getUrl();
                ImageLoaderUtil.getInstance().displayFromNetDCircular(BindCarActivity.this,rightImg,iv_car_right,R.mipmap.cte_logo);
                break;
        }
    }

    @Override
    public void onAddCarSuccess(BaseModel<Object> data) {
        ToastUtil.makeText(BindCarActivity.this,"添加成功"+data.getErrmsg());
        finish();
    }

    @Override
    public void onGetDataFail() {

        dissMissDialog();
    }
}
