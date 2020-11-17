package com.younge.changetheelectricity.util;

import android.content.Context;
import android.text.TextUtils;

public class LoginUtil {

    public static boolean isLogin(Context context){
        String token = (String) SharedPreferencesUtils.getParam(context,"token","");
        return !TextUtils.isEmpty(token);
    }

}
