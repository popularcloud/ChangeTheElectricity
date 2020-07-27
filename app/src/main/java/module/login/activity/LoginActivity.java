package module.login.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.wildma.pictureselector.PictureSelector;
import com.younge.changetheelectricity.R;
import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.base.MyBaseActivity;
import com.younge.changetheelectricity.changetheelectricity.Bean.BatteryDetailsBean;
import com.younge.changetheelectricity.changetheelectricity.adapter.BatteryDetailsAdapter;
import com.younge.changetheelectricity.main.MainActivity;
import com.younge.changetheelectricity.util.SharedPreferencesUtils;
import com.younge.changetheelectricity.util.ToastUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import module.login.bean.LoginBean;
import module.login.presenter.LoginPresenter;
import module.login.view.LoginView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class LoginActivity extends MyBaseActivity<LoginPresenter> implements LoginView {

    @BindView(R.id.et_phone)
    EditText et_phone;
    @BindView(R.id.et_code)
    EditText et_code;
    @BindView(R.id.cb_agree)
    CheckBox cb_agree;

    private BatteryDetailsAdapter mAdapter;

    private List<BatteryDetailsBean> allList = new ArrayList<>();
    private String phone;
    private String code;

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentViewId(Bundle savedInstanceState) {
        return R.layout.activity_login;
    }

    @Override
    protected void init() {

    }


    @Override
    protected void initGetData() {
    }

    @Override
    protected void widgetListener() {

    }


    @OnClick({R.id.tv_code,R.id.tv_login})
    public void onBtnClick(View view){
        switch (view.getId()){
            case R.id.tv_code:

                phone = et_phone.getText().toString().trim();
                if(TextUtils.isEmpty(phone)){
                    ToastUtil.makeText(LoginActivity.this,"请输入手机号");
                    return;
                }

                mPresenter.getPhoneCode(phone);

                break;
            case R.id.tv_login:
                phone = et_phone.getText().toString().trim();
               // code = et_code.getText().toString().trim();
                code = "1111";
                if(TextUtils.isEmpty(phone)){
                    ToastUtil.makeText(LoginActivity.this,"请输入手机号");
                    return;
                }
                if(TextUtils.isEmpty(code)){
                    ToastUtil.makeText(LoginActivity.this,"请输入验证码");
                    return;
                }

                if(!cb_agree.isChecked()){
                    ToastUtil.makeText(LoginActivity.this,"请同意以下服务协议");
                    return;
                }
                //startActivity(new Intent(this, MainActivity.class));
               // mPresenter.loginByCode(phone,code,"");

                /**
                 * create()方法参数一是上下文，在activity中传activity.this，在fragment中传fragment.this。参数二为请求码，用于结果回调onActivityResult中判断
                 * selectPicture()方法参数分别为 是否裁剪、裁剪后图片的宽(单位px)、裁剪后图片的高、宽比例、高比例。都不传则默认为裁剪，宽200，高200，宽高比例为1：1。
                 */
                PictureSelector
                        .create(LoginActivity.this, PictureSelector.SELECT_REQUEST_CODE)
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

               /* RequestBody requestFile =
                        RequestBody.create(MediaType.parse("multipart/form-data"), file);

                MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), requestFile);*/

                MultipartBody.Builder builder = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)//表单类型
                        .addFormDataPart("HTTP_API", "api/common/upload");
                RequestBody photoRequestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                builder.addFormDataPart("file", file.getName(), photoRequestBody);

                mPresenter.uploadPicToService(builder.build().parts());
            }
        }
    }

    @Override
    public void onLoginSuccess(BaseModel<LoginBean> data) {
        ToastUtil.makeText(LoginActivity.this,"登录成功");
        SharedPreferencesUtils.setParam(LoginActivity.this,"token",data.getData().getToken());
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void ongetCodeSuccess(BaseModel<Object> data) {
        ToastUtil.makeText(LoginActivity.this,"验证码已发送");
    }

    @Override
    public void onUplaod(BaseModel<Object> data) {

    }

    @Override
    public void onGetDataFail() {
        ToastUtil.makeText(LoginActivity.this,"获取失败");
    }
}
