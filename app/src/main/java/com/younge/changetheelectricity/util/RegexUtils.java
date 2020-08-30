package com.younge.changetheelectricity.util;
 
import java.util.regex.Pattern;

public class RegexUtils {
 
    /**
     *  正则：手机号
     * @param in
     * @return
     */
    public static boolean validateMobilePhone(String in) {
        Pattern pattern = Pattern.compile("^[1][3578][0-9]{9}$");
        return pattern.matcher(in).matches();
    }
}