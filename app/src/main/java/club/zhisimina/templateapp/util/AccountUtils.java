package club.zhisimina.templateapp.util;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 账号工具类
 */
public class AccountUtils {
    /* 密码最小长度 */
    private static final int passwordMinLen = 1;
    /* 密码最大长度 */
    private static final int passwordMaxLen = 100;

    /**
     * 验证验证码
     *
     * @param rightCode 正确的验证码
     * @param userCode  用户输入的验证码
     * @param context   Context
     * @param view      验证失败时被定位控件
     * @return true:验证通过；false:验证失败
     */
    public static boolean checkValidateCode(String rightCode, String userCode, Context context, View view) {
        // 去空格
        rightCode = rightCode.trim();
        userCode = userCode.trim();
        // 正确的验证码为空
        if (TextUtils.isEmpty(rightCode)) {
            InfoUtils.showInfo(context, "请获取验证码");
            UIUtils.showInputMethod(context, view, true);
            return false;
        }
        // 两个验证码不一致
        if (!rightCode.equals(userCode)) {
            InfoUtils.showInfo(context, "请输入正确的验证码");
            UIUtils.showInputMethod(context, view, true);
            return false;
        }

        // 验证通过
        return true;
    }

    /**
     * 验证手机号
     *
     * @param phoneNumber 手机号
     * @param context     Context
     * @param view        验证失败时被定位控件
     * @return true:验证通过；false:验证失败
     */
    public static boolean checkPhoneNum(String phoneNumber, Context context, View view) {
        // 去空格
        phoneNumber = phoneNumber.trim();
        // 手机号为空
        if (TextUtils.isEmpty(phoneNumber)) {
            InfoUtils.showInfo(context, "请输入手机号");
            UIUtils.showInputMethod(context, view, true);
            return false;
        }
        // 匹配手机号格式
        // 大陆号
        String regExp = "^((13[0-9])|(15[0-9])|(18[0-9])|(17[0-9])|(147))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(phoneNumber);
        // 香港号
        String regExp2 = "^(5|6|8|9)\\d{7}$";
        Pattern p2 = Pattern.compile(regExp2);
        Matcher m2 = p2.matcher(phoneNumber);
        // 格式不匹配
        if (!m.matches() && !m2.matches()) {
            InfoUtils.showInfo(context, "请输入正确的手机号");
            UIUtils.showInputMethod(context, view, true);
            return false;
        }

        // 通过验证
        return true;
    }

    /**
     * 验证密码和确认密码
     *
     * @param pwd        密码
     * @param cfmPwd     确认密码
     * @param context    Context
     * @param pwdView    密码验证失败时被定位控件
     * @param cfmPwdView 确认密码验证失败时被定位控件
     * @return true:验证通过；false:验证失败
     */
    public static boolean checkPwdAndCfmPwd(String pwd, String cfmPwd, Context context, View pwdView, View cfmPwdView) {
        // 去空格
        pwd = pwd.trim();
        cfmPwd = cfmPwd.trim();

        // 验证密码
        if (!checkPassword(pwd, context, pwdView)) {
            return false;
        }

        // 比较密码和确认密码
        if (!pwd.equals(cfmPwd)) {
            InfoUtils.showInfo(context, "两次密码不一致");
            UIUtils.showInputMethod(context, cfmPwdView, true);
            return false;
        }

        // 验证通过
        return true;
    }

    /**
     * 验证密码
     *
     * @param password 密码
     * @param context  Context
     * @param view     验证失败时被定位控件
     * @return true:验证通过；false:验证失败
     */
    public static boolean checkPassword(String password, Context context, View view) {
        // 去空格
        password = password.trim();
        // 密码为空
        if (TextUtils.isEmpty(password)) {
            InfoUtils.showInfo(context, "请输入密码");
            UIUtils.showInputMethod(context, view, true);
            return false;
        }
        // 长度小于最小长度或大于最大长度
        if (password.length() < passwordMinLen || password.length() > passwordMaxLen) {
            InfoUtils.showInfo(context, "请输入" + passwordMinLen + "-" + passwordMaxLen + "位字母、数字、符号");
            UIUtils.showInputMethod(context, view, true);
            return false;
        }

        // 通过验证
        return true;
    }
}
