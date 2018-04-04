package club.zhisimina.templateapp.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 处理日期时间的工具类
 */
public class DateUtils {
    /**
     * 默认的格式化时间日期的模式
     */
    private static String defaultPattern = "yyyy-MM-dd";
    /**
     * 日期格式转换工具
     */
    private static SimpleDateFormat sdf = new SimpleDateFormat(defaultPattern, Locale.CHINA);
    /**
     * 进行格式转换的日期
     */
    private static Date date = new Date();

    private DateUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");

    }

    /**
     * 将毫秒值转换为格式化后的时间的字符串
     *
     * @param pattern 格式化时间的模式, 如果为null或"", 默认模式为"yyyy-MM-dd HH:mm:ss"
     * @param millis  时间（毫秒）
     * @return 格式化后的时间的字符串
     */
    public static String getFormattedTime(String pattern, long millis) {
        // 如果模式不为空，则使用指定模式格式化时间，否则，使用默认模式
        if (pattern != null && !"".equals(pattern)) {
            sdf.applyPattern(pattern);
        }

        // 设置要格式化的时间
        date.setTime(millis);

        // 执行格式化，并返回
        return sdf.format(date);
    }

    /**
     * 获取当前日期  格式：月日年，例：06112017
     * @return 当前日期
     */
    public static String getCurrDate(){
        date = new Date();
        sdf.applyPattern("MMddyyyy");
        return sdf.format(date);
    }
}
