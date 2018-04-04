package club.zhisimina.templateapp.util;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import static com.yanzhenjie.nohttp.NoHttp.getContext;

/**
 * Created by hankehui on 2017/12/25.
 * 通用工具类
 */

public class GeneralUtils {
    /**
     * 获取控件焦点
     * @param view     要获取焦点的view
     */
    public static void ganFocus(View view){
        view.setFocusable(true);
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.requestFocusFromTouch();

    }

    /**
     * 打开软键盘
     * @param editText    控件
     */
    public static void  openSoftKeyboard(EditText editText){
        InputMethodManager inputManager = (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);//获取软键盘
        inputManager.showSoftInput(editText, 0);//打开软件盘   不使用自动判断打开和关闭软键盘方法（有问题，自己关闭的不认同，isActive()会一直为true）
    }

    /**
     * 关闭软键盘
     * @param view  控件
     */
    public static void closeSoftKeyboard(View view){
        InputMethodManager m = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);//获取软键盘
        m.hideSoftInputFromWindow(view.getWindowToken(), 0);//关闭软件盘   不使用自动判断打开和关闭软键盘方法（有问题，自己关闭的不认同，isActive()会一直为true）
    }

    /**
     * 获取焦点并打开软键盘
     * @param view  控件
     */
    public static void getFocusAndOpenSoftKeyboard(View view){
        ganFocus(view);//获取焦点
    InputMethodManager inputManager = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);//获取软键盘
    inputManager.showSoftInput(view, 0);//打开软件盘   不使用自动判断打开和关闭软键盘方法（有问题，自己关闭的不认同，isActive()会一直为true）
}
    /**
     * 失去焦点并关闭软键盘
     * @param view  控件
     */
    public static void closeFocusAndCloseSoftKeyboard(View view){
        view.setFocusable(false);
        view.setFocusableInTouchMode(false);
        InputMethodManager inputManager = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);//获取软键盘
        inputManager.hideSoftInputFromWindow(view.getWindowToken(), 0);//打开软件盘   不使用自动判断打开和关闭软键盘方法（有问题，自己关闭的不认同，isActive()会一直为true）
    }

    /**
     * 选中一个控件，其他按钮取消选中状态（选中的控件已被选择，则取消选中状态）
     * @param view                     选中的按钮
     * @param checkedBackground        选中时的背景颜色
     * @param uncheckedBackground      未选中时的背景颜色
     * @param checked                  对应状态数组的下表
     * @param views                    控件数据
     * @param booleans                 状态数组
     * @return  返回数据
     */
    public static boolean[] checkedMoney(View view, int checkedBackground, int uncheckedBackground, int checked, View[] views, boolean[] booleans){
        if (!booleans[checked]){
            booleans=GeneralUtils.initViewChecked(view,uncheckedBackground,views,booleans,booleans[checked]);
            booleans[checked]=true;
            view.setBackgroundResource(checkedBackground);
        }else {
            booleans[checked]=false;
            view.setBackgroundResource(uncheckedBackground);
        }
        return booleans;
    }
    /**
     * 选中一个控件，其他按钮取消选中状态（选中的控件已被选择，则取消选中状态）  适用于textView 和button
     * @param view                     选中的按钮
     * @param checkedBackground        选中时的背景颜色
     * @param uncheckedBackground      未选中时的背景颜色
     * @param checked                  对应状态数组的下表
     * @param views                    控件数据
     * @param booleans                 状态数组
     * @param checkedColor             选中时的字体颜色
     * @param uncheckedColor           未选中时的字体颜色
     * @return  返回数据
     */
    public static boolean[] checkedMoney(TextView view, int checkedBackground, int uncheckedBackground, int checked, TextView[] views, boolean[] booleans,int checkedColor,int uncheckedColor){
        if (!booleans[checked]){
            booleans=GeneralUtils.initViewChecked(view,uncheckedBackground,views,booleans,booleans[checked],uncheckedColor);
            booleans[checked]=true;
            view.setTextColor(view.getResources().getColor(checkedColor));
            view.setBackgroundResource(checkedBackground);
        }else {
            booleans[checked]=false;
            view.setBackgroundResource(uncheckedBackground);
            view.setTextColor(view.getResources().getColor(uncheckedColor));
        }
        return booleans;
    }
    /**
     * 选中一个控件，其他按钮取消选中状态（选中的控件不改变）  适用于textView 和button
     * @param view                     选中的按钮
     * @param checkedBackground        选中时的背景颜色
     * @param uncheckedBackground      未选中时的背景颜色
     * @param checked                  对应状态数组的下表
     * @param views                    控件数据
     * @param booleans                 状态数组
     * @param checkedColor             选中时的字体颜色
     * @param uncheckedColor           未选中时的字体颜色
     * @return  返回数据
     */
    public static boolean[] checkedMoneys(TextView view, int checkedBackground, int uncheckedBackground, int checked, TextView[] views, boolean[] booleans,int checkedColor,int uncheckedColor){
        if (!booleans[checked]){
            booleans=GeneralUtils.initViewChecked(view,uncheckedBackground,views,booleans,booleans[checked],uncheckedColor);
            booleans[checked]=true;
            view.setTextColor(view.getResources().getColor(checkedColor));
            view.setBackgroundResource(checkedBackground);
        }
        return booleans;
    }
     /**
     * 改变一堆控件背景颜色和其状态
     * @param background    背景颜色
     * @param views         控件数组
     * @param booleans      状态数组
     * @param checked       状态
     * @return 返回改变的状态
     */
    public static boolean[] initViewChecked(int background, View[] views, boolean[] booleans, boolean checked){
        for (int i = 0; i <views.length ; i++) {
            views[i].setBackgroundResource(background);
            booleans[i]=checked;
        }
        return booleans;
    }
    /**
     * 改变一堆控件背景颜色、其状态和字体颜色
     * @param background    背景颜色
     * @param views         控件数组
     * @param booleans      状态数组
     * @param checked       状态
     * @return 返回改变的状态
     */
    public static boolean[] initViewChecked(int background, TextView[] views, boolean[] booleans, boolean checked,int color){
        for (int i = 0; i <views.length ; i++) {
            views[i].setBackgroundResource(background);
            views[i].setTextColor(views[i].getResources().getColor(color));
            booleans[i]=checked;
        }
        return booleans;
    }
    /**
     * 改变一堆控件背景颜色和其状态
     * @param view          不改变背景颜色和图片的控件
     * @param background    背景颜色
     * @param views         控件数组
     * @param booleans      状态数组
     * @param checked       状态
     * @return 返回改变的状态
     */
    public static boolean[] initViewChecked(View view, int background, View[] views, boolean[] booleans, boolean checked){
        for (int i = 0; i <views.length ; i++) {
            if (view!=views[i]){
                views[i].setBackgroundResource(background);
                booleans[i]=checked;
            }
        }
        return booleans;
    }
    /**
     * 改变一堆控件背景颜色和其状态
     * @param view          不改变背景颜色和图片的控件
     * @param background    背景颜色
     * @param views         控件数组
     * @param booleans      状态数组
     * @param checked       状态
     * @param color         字体颜色
     * @return 返回改变的状态
     */
    public static boolean[] initViewChecked(TextView view, int background, TextView[] views, boolean[] booleans, boolean checked,int color){
        for (int i = 0; i <views.length ; i++) {
            if (view!=views[i]){
                views[i].setBackgroundResource(background);
                views[i].setTextColor(views[i].getResources().getColor(color));
                booleans[i]=checked;
            }
        }
        return booleans;
    }
/***************************************************************************/
    /**
     * 显示网络头像
     * @param url            地址
     * @param imageView      显示控件
     */
    public static void setUrlImg(String url, ImageView imageView){
    if (null!=url&&!url.equals("")){//判断是否为空或者为""
        Glide.with(getContext()).load(url).into(imageView);//用户头像
    }
}

    /**
     * 显示内容
     * @param data        内容
     * @param view        控件
     */
    public static void setText(String data,TextView view){
    if (null!=data&&!data.equals("")){//判断是否为空或者为""
        view.setText(data);//显示内容
    }
}

public static Double stringtoDouble(String data){
        if (data!=null&&!data.equals("")){
            return Double.valueOf(data);
        }
            return 0.00;
}
}
