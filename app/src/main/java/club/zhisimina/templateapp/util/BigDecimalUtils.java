package club.zhisimina.templateapp.util;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * Created by Administrator on 2017/12/18.
 * double精度解决工具类
 */

public class BigDecimalUtils {
    public static double add(double d1,double d2){
        BigDecimal b1=new BigDecimal(Double.toString(d1));
        BigDecimal b2=new BigDecimal(Double.toString(d2));
        return b1.add(b2).doubleValue();

    }

    public static double sub(double d1,double d2){
        BigDecimal b1=new BigDecimal(Double.toString(d1));
        BigDecimal b2=new BigDecimal(Double.toString(d2));
        return b1.subtract(b2, new MathContext(1)).doubleValue();

    }

    public static double mul(double d1,double d2){
        BigDecimal b1=new BigDecimal(Double.toString(d1));
        BigDecimal b2=new BigDecimal(Double.toString(d2));
        return b1.multiply(b2,new MathContext(10, RoundingMode.valueOf(BigDecimal.ROUND_HALF_UP))).doubleValue();

    }

    public static double div(double d1,double d2){

        return div(d1,d2,10);

    }

    public static double div(double d1,double d2,int scale){
        if(scale<0){
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b1=new BigDecimal(Double.toString(d1));
        BigDecimal b2=new BigDecimal(Double.toString(d2));
        return b1.divide(b2,scale,BigDecimal.ROUND_HALF_UP).doubleValue();

    }
}
