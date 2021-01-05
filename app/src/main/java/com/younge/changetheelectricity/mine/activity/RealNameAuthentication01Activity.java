package com.younge.changetheelectricity.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.wildma.pictureselector.PictureSelector;
import com.younge.changetheelectricity.R;
import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.base.MyBaseActivity;
import com.younge.changetheelectricity.changetheelectricity.Bean.BatteryDetailsBean;
import com.younge.changetheelectricity.changetheelectricity.adapter.BatteryDetailsAdapter;
import com.younge.changetheelectricity.mine.bean.ReturnImgUrlBean;
import com.younge.changetheelectricity.mine.bean.UserInfoBean;
import com.younge.changetheelectricity.mine.presenter.RealNameAuthenticationPresenter;
import com.younge.changetheelectricity.mine.view.RealNameAuthenticationCenterView;
import com.younge.changetheelectricity.util.ImageLoaderUtil;
import com.younge.changetheelectricity.util.JsonUtil;
import com.younge.changetheelectricity.util.RegexUtils;
import com.younge.changetheelectricity.util.SharedPreferencesUtils;
import com.younge.changetheelectricity.util.ToastUtil;
import com.younge.changetheelectricity.widget.CustomDialog;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class RealNameAuthentication01Activity extends MyBaseActivity<RealNameAuthenticationPresenter> implements RealNameAuthenticationCenterView {

    @BindView(R.id.tv_center_title)
    TextView tv_center_title;
    @BindView(R.id.tv_submit)
    TextView tv_submit;
    @BindView(R.id.et_name)
    EditText et_name;
    @BindView(R.id.et_idCard)
    EditText et_idCard;
    @BindView(R.id.iv_card_positive)
    ImageView iv_card_positive;
    @BindView(R.id.iv_card_back)
    ImageView iv_card_back;
    @BindView(R.id.iv_card_hold)
    ImageView iv_card_hold;
    private BatteryDetailsAdapter mAdapter;

    private CustomDialog customDialog;

    private List<BatteryDetailsBean> allList = new ArrayList<>();

    private int imgType;

    private String positiveImg;
    private String backImg;
    private String holdImg;

    @Override
    protected RealNameAuthenticationPresenter createPresenter() {
        return new RealNameAuthenticationPresenter(this);
    }


    @Override
    protected int getContentViewId(Bundle savedInstanceState) {
        return R.layout.activity_real_name_authentication01;
    }

    @Override
    protected void init() {
        tv_center_title.setText("实名认证");
    }

    @Override
    protected void initGetData() {

    }

    @Override
    protected void widgetListener() {

    }

    @OnClick({R.id.rl_fanhui_left,R.id.tv_submit,R.id.iv_card_positive,R.id.iv_card_back,R.id.iv_card_hold})
    public void onBtnClick(View view){
        switch (view.getId()){
            case R.id.rl_fanhui_left:
                onBackShow();
                break;
            case R.id.tv_submit:

                String name = et_name.getText().toString().trim();
                String idCard = et_idCard.getText().toString().trim();

                if(TextUtils.isEmpty(name)){
                    ToastUtil.makeText(RealNameAuthentication01Activity.this,"请填写真实姓名");
                    return;
                }

                if(!RegexUtils.isID(idCard)){
                    ToastUtil.makeText(RealNameAuthentication01Activity.this,"请填写正确身份证号");
                    return;
                }


                if(TextUtils.isEmpty(positiveImg)){
                    ToastUtil.makeText(RealNameAuthentication01Activity.this,"请选择身份证正面照");
                    return;
                }


                if(TextUtils.isEmpty(backImg)){
                    ToastUtil.makeText(RealNameAuthentication01Activity.this,"请选择身份证背面照");
                    return;
                }


                if(TextUtils.isEmpty(holdImg)){
                    ToastUtil.makeText(RealNameAuthentication01Activity.this,"请选择手持身份证照");
                    return;
                }


                customDialog = new CustomDialog(this);
                customDialog.setTitle("确认身份信息");
                customDialog.setMessage("姓名："+name + "\n身份证号："+idCard);
                customDialog.setButton1Text("确认");
                customDialog.setButton2Text("取消");
                customDialog.setCanceledOnTouchOutside(true);
                customDialog.setEnterBtn(new CustomDialog.OnClickListener() {
                    @Override
                    public void onClick(CustomDialog dialog, int id, Object object) {
                        customDialog.dismiss();
                        if(mPresenter != null){
                            mPresenter.realNameAuthentication(name,idCard,positiveImg,backImg,holdImg, (String) SharedPreferencesUtils.getParam(RealNameAuthentication01Activity.this,"token",""));
                        }
                    }
                });
                customDialog.setCancelBtn(new CustomDialog.OnClickListener() {
                    @Override
                    public void onClick(CustomDialog dialog, int id, Object object) {
                        customDialog.dismiss();


                        customDialog = new CustomDialog(RealNameAuthentication01Activity.this);
                        customDialog.setTitle("提示");
                        customDialog.setMessage("不实名认证将不能使用换电服务");
                        customDialog.setButton1Text("继续认证");
                        customDialog.setButton2Text("稍后认证");
                        customDialog.setCanceledOnTouchOutside(true);
                        customDialog.setEnterBtn(new CustomDialog.OnClickListener() {
                            @Override
                            public void onClick(CustomDialog dialog, int id, Object object) {
                                customDialog.dismiss();
                                if(mPresenter != null){
                                    mPresenter.realNameAuthentication(name,idCard,positiveImg,backImg,holdImg, (String) SharedPreferencesUtils.getParam(RealNameAuthentication01Activity.this,"token",""));
                                }
                            }
                        });
                        customDialog.setCancelBtn(new CustomDialog.OnClickListener() {
                            @Override
                            public void onClick(CustomDialog dialog, int id, Object object) {
                                customDialog.dismiss();
                            }
                        });
                        customDialog.show();

                    }
                });
                customDialog.show();




                break;
            case R.id.iv_card_positive:
                imgType = 0;
                /**
                 * create()方法参数一是上下文，在activity中传activity.this，在fragment中传fragment.this。参数二为请求码，用于结果回调onActivityResult中判断
                 * selectPicture()方法参数分别为 是否裁剪、裁剪后图片的宽(单位px)、裁剪后图片的高、宽比例、高比例。都不传则默认为裁剪，宽200，高200，宽高比例为1：1。
                 */
                PictureSelector
                        .create(RealNameAuthentication01Activity.this, PictureSelector.SELECT_REQUEST_CODE)
                        .selectPicture(true, 200, 200, 1, 1);
                break;
            case R.id.iv_card_back:
                imgType = 1;
                PictureSelector
                        .create(RealNameAuthentication01Activity.this, PictureSelector.SELECT_REQUEST_CODE)
                        .selectPicture(true, 200, 200, 1, 1);
                break;
            case R.id.iv_card_hold:
                imgType = 2;
                PictureSelector
                        .create(RealNameAuthentication01Activity.this, PictureSelector.SELECT_REQUEST_CODE)
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
    public void onSubmitSuccess(BaseModel<Object> data) {
        ToastUtil.makeText(this,"认证信息提交成功！");
        mPresenter.getPersonalInfo((String) SharedPreferencesUtils.getParam(this,"token",""));
       // finish();
    }

    @Override
    public void onUploadPicSuccess(BaseModel<ReturnImgUrlBean> data) {

        dissMissDialog();

        switch (imgType){
            case 0:
                positiveImg = data.getData().getUrl();
                ImageLoaderUtil.getInstance().displayFromNetDCircular(RealNameAuthentication01Activity.this,positiveImg,iv_card_positive,R.mipmap.cte_logo);
                break;
            case 1:
                backImg = data.getData().getUrl();
                ImageLoaderUtil.getInstance().displayFromNetDCircular(RealNameAuthentication01Activity.this,backImg,iv_card_back,R.mipmap.cte_logo);
                break;
            case 2:
                holdImg = data.getData().getUrl();
                ImageLoaderUtil.getInstance().displayFromNetDCircular(RealNameAuthentication01Activity.this,holdImg,iv_card_hold,R.mipmap.cte_logo);
                break;
        }
    }


    private void onBackShow(){
        customDialog = new CustomDialog(RealNameAuthentication01Activity.this);
        customDialog.setTitle("提示");
        customDialog.setMessage("不实名认证将不能使用换电服务");
        customDialog.setButton1Text("继续认证");
        customDialog.setButton2Text("稍后认证");
        customDialog.setCanceledOnTouchOutside(true);
        customDialog.setEnterBtn(new CustomDialog.OnClickListener() {
            @Override
            public void onClick(CustomDialog dialog, int id, Object object) {
                customDialog.dismiss();
            }
        });
        customDialog.setCancelBtn(new CustomDialog.OnClickListener() {
            @Override
            public void onClick(CustomDialog dialog, int id, Object object) {
                customDialog.dismiss();
                finish();
            }
        });
        customDialog.show();
    }

    @Override
    public void onGetPersonInfo(BaseModel<UserInfoBean> data) {

        if(data != null && data.getData() != null && data.getData().getUserinfo() != null){
            String userInfoDetail = JsonUtil.parserObjectToGson(data.getData().getUserinfo());
            SharedPreferencesUtils.setParam(this,"userInfoDetail",userInfoDetail);
        }

        finish();

    }

    @Override
    public void onGetDataFail() {

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) { //按下的如果是BACK，同时没有重复
            onBackShow();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBack(View view) {

    }
}
