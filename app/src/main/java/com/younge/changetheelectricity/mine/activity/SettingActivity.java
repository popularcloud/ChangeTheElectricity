package com.younge.changetheelectricity.mine.activity;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.younge.changetheelectricity.R;
import com.younge.changetheelectricity.base.BaseActivity;
import com.younge.changetheelectricity.base.BaseModel;
import com.younge.changetheelectricity.base.MyBaseActivity;
import com.younge.changetheelectricity.mine.bean.PackageBean;
import com.younge.changetheelectricity.mine.bean.UserInfoBean;
import com.younge.changetheelectricity.mine.presenter.MinePresenter;
import com.younge.changetheelectricity.mine.view.MineView;
import com.younge.changetheelectricity.util.SharedPreferencesUtils;
import com.younge.changetheelectricity.util.ToastUtil;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import module.login.activity.LoginActivity;
import module.login.activity.WebViewActivity;

/**
 * 设置
 */
public class SettingActivity extends MyBaseActivity<MinePresenter> implements MineView {

	@BindView(R.id.tv_exit)
    TextView tv_exit;


	private UserInfoBean user;

	@Override
	protected MinePresenter createPresenter() {
		return new MinePresenter(this);
	}

	@Override
	protected int getContentViewId(Bundle savedInstanceState) {
		return R.layout.activity_setting;
	}

	@Override
	protected void init() {
		setTitle("设置");
	}

	@Override
	protected void initGetData() {

	}

	@Override
	protected void widgetListener() {

	}

	@Override
	public void onGetDataSuccess(BaseModel<UserInfoBean> data) {

	}

	@Override
	public void onGetDataFail() {

	}

	@OnClick({R.id.rl_fanhui_left,R.id.tv_exit,R.id.txt_agreement,R.id.txt_privacy})
	public void onBtnClick(View view){
		switch (view.getId()){
			case R.id.rl_fanhui_left:
				finish();
				break;
			case R.id.tv_exit:
				SharedPreferencesUtils.setParam(SettingActivity.this,"token","");
				startActivity(new Intent(this, LoginActivity.class));
				finish();
				break;
			case R.id.txt_agreement:
				Intent intent = new Intent(SettingActivity.this, WebViewActivity.class);
				intent.putExtra("url","http://gxyc.jxcqs.com/shownode.aspx?nid=32");
				intent.putExtra("title","用户协议");
				startActivity(intent);
				break;
			case R.id.txt_privacy:
				Intent intent2 = new Intent(SettingActivity.this, WebViewActivity.class);
				intent2.putExtra("url","http://gxyc.jxcqs.com/shownode.aspx?nid=33");
				intent2.putExtra("title","隐私协议");
				startActivity(intent2);
				break;
		}
	}
}
