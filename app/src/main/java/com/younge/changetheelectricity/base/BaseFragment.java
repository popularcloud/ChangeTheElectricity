package com.younge.changetheelectricity.base;

import android.content.Context;
import android.view.View;

import androidx.fragment.app.Fragment;

import com.younge.changetheelectricity.util.LoadingDialog;
import com.younge.changetheelectricity.util.ToastUtil;


/**
 * File descripition:   ftagment 基类
 *
 * @author lp
 * @date 2018/6/19
 */

public abstract class BaseFragment extends Fragment {
    public View view;
    public Context mContext;
    private LoadingDialog loadingDialog;



    public void showLoadingDialog() {
        showLoadingDialog("加载中...");
    }

    /**
     * 加载  黑框...
     */
    public void showLoadingDialog(String msg) {
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(getContext());
            loadingDialog.setCancelable(false);
        }
        loadingDialog.setMessage(msg);
        if (!loadingDialog.isShowing()) {
            loadingDialog.show();
        }
    }

    /**
     * 消失  黑框...
     */
    public void dissMissDialog() {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.view = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
