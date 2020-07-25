package com.younge.changetheelectricity.base;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.fragment.app.FragmentActivity;

import com.younge.changetheelectricity.R;
import com.younge.changetheelectricity.util.LoadingDialog;
import com.younge.changetheelectricity.util.ToastUtil;

import butterknife.ButterKnife;


/**
 *
 */
public abstract class MyBaseActivity<P extends BasePresenter> extends FragmentActivity implements BaseView {

	public Bundle savedInstanceState;
	protected TextView txtActionbarTitle;
	private TextView tvqd;

	protected P mPresenter;


	private LoadingDialog loadingDialog;

	protected abstract P createPresenter();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.savedInstanceState = savedInstanceState;
		int layoutId = getContentViewId(savedInstanceState);

		setContentView(layoutId);
		ButterKnife.bind(this);

		mPresenter = createPresenter();

		try {
			txtActionbarTitle = (TextView) findViewById(R.id.tv_center_title);
			tvqd = (TextView) findViewById(R.id.tv_right);
		} catch (Exception e){}

		/**
		 * 初始化
		 */
		init();

		/**
		 * 设置控件的监听
		 */
		widgetListener();

		/**
		 * 获取数据
		 */
		initGetData();

		initStatus();

	}

	public void initStatus() {
/*		ImmersionBar.with(this)
				.statusBarColor(R.color.white)
				.statusBarDarkFont(true)
				.navigationBarColor(R.color.white).init();*/
	}

	protected void setTitle(String title) {
		if (txtActionbarTitle != null && title != null) {
			txtActionbarTitle.setText(title);
		}
	}

	protected void setTitle(String title,int textColor) {
		if (txtActionbarTitle != null && title != null) {
			txtActionbarTitle.setText(title);
			txtActionbarTitle.setTextColor(textColor);
		}
	}

	protected void setRight(String right, View.OnClickListener listener) {
		if (tvqd != null && right != null) {
			tvqd.setVisibility(View.VISIBLE);
			tvqd.setText(right);
			tvqd.setOnClickListener(listener);
		}
	}
	protected void setRight(String right,String color ,View.OnClickListener listener) {
		if (tvqd != null && right != null) {
			tvqd.setVisibility(View.VISIBLE);
			tvqd.setText(right);
			tvqd.setTextColor(Color.parseColor(color));
			tvqd.setOnClickListener(listener);
		}
	}
	protected void setRight(String right) {
		if (tvqd != null && right != null) {
			tvqd.setVisibility(View.VISIBLE);
			tvqd.setText(right);
		}
	}
/*	protected void setRight(int right, View.OnClickListener listener) {
		if (imgRight != null && right != 0) {
			imgRight.setVisibility(View.VISIBLE);
			imgRight.setImageResource(right);
			imgRight.setOnClickListener(listener);
		}
	}*/

	public void onBack(View view){
		finish();
	}

	/**
	 * 获取显示view的xml文件ID
	 * 
	 * 在Activity的 {@link #onCreate(Bundle)}里边被调用
	 * 
	 * @version 1.0
	 * @param savedInstanceState
	 * @createTime 2014年4月21日,下午2:31:19
	 * @updateTime 2014年4月21日,下午2:31:19
	 * @createAuthor CodeApe
	 * @updateAuthor CodeApe
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * 
	 * @return xml文件ID
	 */
	protected abstract int getContentViewId(Bundle savedInstanceState);


	/**
	 * 初始化应用程序，设置一些初始化数据都获取数据等操作
	 * 
	 * 在{@link #widgetListener()}之后被调用
	 * 
	 * @version 1.0
	 * @createTime 2014年4月21日,下午1:55:15
	 * @updateTime 2014年4月21日,下午1:55:15
	 * @createAuthor CodeApe
	 * @updateAuthor CodeApe
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * 
	 */
	protected abstract void init();

	/**
	 * 获取上一个界面传送过来的数据
	 * 
	 * 在{@link BaseActivity#init()}之前被调用
	 * 
	 * @version 1.0
	 * @createTime 2014年4月21日,下午2:42:56
	 * @updateTime 2014年4月21日,下午2:42:56
	 * @createAuthor CodeApe
	 * @updateAuthor CodeApe
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * 
	 */
	protected abstract void initGetData();

	/**
	 * 组件监听模块
	 * 
	 * @version 1.0
	 * @createTime 2014年4月21日,下午1:56:06
	 * @updateTime 2014年4月21日,下午1:56:06
	 * @createAuthor CodeApe
	 * @updateAuthor CodeApe
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * 
	 */
	protected abstract void widgetListener();

	public void showError(String msg) {
		showToast(msg);
	}

	@Override
	public void showLoading() {
		showLoadingDialog();
	}

	@Override
	public void hideLoading() {
		dissMissDialog();
	}

	public void showToast(String str) {
		ToastUtil.makeText(this,str);
	}

	public void showLoadingDialog() {
		showLoadingDialog("加载中...");
	}


	/**
	 * 加载  黑框...
	 */
	public void showLoadingDialog(String msg) {
		if (loadingDialog == null) {
			loadingDialog = new LoadingDialog(this);
			loadingDialog.setCancelable(false);
//            loadingDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
//                @Override
//                public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
//                    if (keyCode == KeyEvent.KEYCODE_SEARCH) {
//                        return true;
//                    } else {
//                        return false; //默认返回 false
//                    }
//                }
//            });
		}
		loadingDialog.setMessage(msg);
		if (!loadingDialog.isShowing()) {
			loadingDialog.show();
		}
	}


	/**
	 * 消失 加载  黑框...
	 */
	public void dissMissDialog() {
		if (loadingDialog != null) {
			loadingDialog.dismiss();
		}
	}
}
