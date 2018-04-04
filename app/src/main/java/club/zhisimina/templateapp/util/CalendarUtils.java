package club.zhisimina.templateapp.util;

import android.app.DatePickerDialog;
import android.content.Context;
import android.text.format.DateFormat;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;


/**
 * @description  日历工具类
 * @author  hankehi
 * @date  2018/1/10.
 */
public class CalendarUtils {
    /**
     * 调用系统日期选择器
     *
     * @param textView 要显示时间的控件
     */
    public static void getTime(Context context,final TextView textView) {
        final Calendar c = Calendar.getInstance();//获取默认日历
        DatePickerDialog dialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {//创建日期选择器
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                c.set(year, monthOfYear, dayOfMonth);//获取时间
                textView.setText(DateFormat.format("yyyy-MM-dd", c));//显示时间
            }
        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
        dialog.show();//显示时间选择器
    }

    /**
     * 显示当前时间
     *
     * @param textView 要显示时间的控件
     */
    public static void setTime(Context context,final TextView textView) {
        final Calendar c = Calendar.getInstance();//获取默认日历
        String s = textView.getText().toString();
        String[] split = s.split("-");
        DatePickerDialog dialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {//创建日期选择器
                c.set(year, monthOfYear, dayOfMonth);//获取时间
                textView.setText(DateFormat.format("yyyy-MM-dd", c));//显示时间
            }
        }, Integer.parseInt(split[0]), (Integer.parseInt(split[1]) - 1), Integer.parseInt(split[split.length - 1]));
        dialog.show();//显示时间选择器
    }
}
