package com.younge.changetheelectricity.util;
 
import java.util.regex.Matcher;
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

    /**
     * 判断是否是身份证
     *
     * @param idNum 身份证号码
     * @return 是，true ； 否 ， false
     */
    public static boolean isID(String idNum) {
        // 定义判别用户身份证号的正则表达式（要么是15位，要么是18位，最后一位可以为字母）
        Pattern idNumPattern = Pattern.compile("(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])");
        // 通过Pattern获得Matcher
        Matcher idNumMatcher = idNumPattern.matcher(idNum);
        // 判断用户输入是否为身份证号
        return idNumMatcher.matches();
    }
}