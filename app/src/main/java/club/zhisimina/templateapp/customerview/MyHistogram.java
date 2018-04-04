package club.zhisimina.templateapp.customerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

import club.zhisimina.templateapp.R;
import club.zhisimina.templateapp.common.DataEvent;
import club.zhisimina.templateapp.util.LogUtils;

import java.util.ArrayList;

/**
 * Created by hankehui on 2018/1/8.
 * 柱状图
 *
 * 当不超出屏幕或者到达边缘停止滑动问题 带实现
 */

public class MyHistogram extends View {
//    //画笔
    Paint paint;
    ArrayList<Paint> paints;
    ArrayList<Rect> rects;
    ArrayList<Integer> colours;//颜色
    //Canvas canvass;
    int width;//画布宽度
    int height;//画布（0,0）到名称的距离
    public MyHistogram(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    public MyHistogram(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyHistogram(Context context) {
        this(context, null);
    }
int initHeight;
    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        // canvas.drawCircle(400, 400, 200, mPaint);
        // canvas.drawText("Hello world!", 200, 300, mPaint);
        super.onDraw(canvas);
        width=canvas.getWidth();//获取画布宽度
        initHeight=canvas.getHeight();//获取画布高度度
        height=canvas.getHeight()-initTextSize-initTextNameInterval;//画布（0,0）到名称的距离
        for (int i = 0; i <colours.size() ; i++) {
        paints.get(i).setColor(colours.get(i));//设置柱状图颜色
        }
        for (int i = 0; i <paints.size() ; i++) {
        // 抗锯齿
            paints.get(i).setAntiAlias(true);
//        Rect targetRect = new Rect(10, 100,110, canvas.getHeight());
        canvas.drawRect(rects.get(i),paints.get(i));//画图
            paints.get(i).setTextSize(initTextSize);//设置字体大小
            paints.get(i).setStrokeWidth(3);
            paints.get(i).setTextAlign(Paint.Align.CENTER);//居中
            if (initTextColor){
            paints.get(i).setColor(initTextNameColor);//设置名称颜色
            }
            canvas.drawText(name.get(i),rects.get(i).centerX(),canvas.getHeight()-initTextNameInterval-5,paints.get(i));
            if (initTextColor) {
                paints.get(i).setColor(initTextNumberColor);//设置数值颜色
            }
            canvas.drawText(number.get(i).toString(),rects.get(i).centerX(),rects.get(i).top-initTextNumberInterval-5,paints.get(i));
        }
    }
    int initColor=0xFFFF0000;//默认颜色
    int initHistogramColor=initColor;//默认柱状图颜色
    int initHistogramWidth=100;//柱状图宽度
    int interval;//间隔
    int histogramMinInterval=15;//柱状图最小间隔
    int initTextSize=35;
    int initTextNameInterval=5;
    int initTextNumberInterval=5;
    int initTextNameColor=initColor;//名称颜色
    int initTextNumberColor=initColor;//数据颜色
    Boolean initTextColor=false;
    public void init(Context context, AttributeSet attrs){//初始化
        paints=new ArrayList<Paint>();
        rects=new ArrayList<Rect>();
        name=new ArrayList<String>();
        number=new ArrayList<Double>();
        altitude=new ArrayList<Long>();
        colours=new ArrayList<Integer>();
        //colours.add(Color.RED);
        altitude.add(0L);
        name.add("");
        number.add( 0.00);
        paint = new Paint();
//        paint.setColor(colours.get(0));
        paints.add(paint);
        Rect targetRect = new Rect(10, 100,110, 300);
        rects.add(targetRect);
        TypedArray ta=getContext().obtainStyledAttributes(attrs, R.styleable.Histogram);
        initColor=ta.getColor(R.styleable.Histogram_defaultColor,initColor);
//        initHistogramWidth=ta.getDimensionPixelSize(R.styleable.Histogram_histogramWidth,initHistogramWidth);
        initHistogramWidth=ta.getInteger(R.styleable.Histogram_histogramWidth,initHistogramWidth);
        histogramMinInterval=ta.getInteger(R.styleable.Histogram_histogramMinInterval,histogramMinInterval);
        initTextSize=ta.getInteger(R.styleable.Histogram_txtSize,initTextSize);
        initTextNameInterval=ta.getInteger(R.styleable.Histogram_txtNameInterval,initTextNameInterval);
        initTextNumberInterval=ta.getInteger(R.styleable.Histogram_txtNumberInterval,initTextNumberInterval);
        colours.add(ta.getColor(R.styleable.Histogram_histogramColor,initColor));
        initHistogramColor=ta.getColor(R.styleable.Histogram_histogramColor,initColor);
        initTextNameColor=ta.getColor(R.styleable.Histogram_txtNameColor,initColor);
        initTextNumberColor=ta.getColor(R.styleable.Histogram_txtNumberColor,initColor);
        initTextColor=ta.getBoolean(R.styleable.Histogram_initTextColor,initTextColor);
        ta.recycle();
    }

    /**
     * 获取柱状图间隔
     * @return  柱状图间隔
     */
    public int getInitHistogramColor() {
        return initHistogramColor;
    }

    /**
     * 设置柱状图间隔
     * @param initHistogramColor     柱状图间隔
     */
    public void setInitHistogramColor(int initHistogramColor) {
        this.initHistogramColor = initHistogramColor;
        colours=new ArrayList<Integer>();
        for (int i = 0; i <size ; i++) {
            colours.add(initColor);
        }
    }

    /**
     * 获取默认颜色
     * @return  默认颜色
     */
    public int getInitColor() {
        return initColor;
    }

    /**
     * 设置默认颜色
     * @param initColor   默认颜色
     */
    public void setInitColor(int initColor) {
        this.initColor = initColor;
        setInitTextNameColor(initColor);
        setInitTextNumberColor(initColor);
        colours=new ArrayList<Integer>();
        for (int i = 0; i <size ; i++) {
        colours.add(initColor);
        }
    }
    /**
     * 获取默认柱状图宽度
     * @return  默认柱状图宽度
     */
    public int getInitHistogramWidth() {
        return initHistogramWidth;
    }

    /**
     * 设置默认柱状图宽度
     * @param initHistogramWidth 默认柱状图宽度
     */
    public void setInitHistogramWidth(int initHistogramWidth) {
        this.initHistogramWidth = initHistogramWidth;
        rects.clear();
        for (int i = 0; i <size ; i++) {
            Rect targetRect = new Rect((initHistogramWidth+interval)*i+interval, altitude.get(i).intValue(),(initHistogramWidth+interval)*(i+1), height-initTextNameInterval);
            rects.add(targetRect);
        }
    }

    /**
     * 获取默认柱状图最小间隔
     * @return   默认柱状图最小间隔
     */
    public int getHistogramMinInterval() {
        return histogramMinInterval;
    }
    /**
     * 设置默认柱状图最小间隔
     * @param histogramMinInterval 默认柱状图最小间隔
     */
    public void setHistogramMinInterval(int histogramMinInterval) {
        this.histogramMinInterval = histogramMinInterval;
        int intervals=(width-initHistogramWidth*size)/(size+1);
        if (intervals<histogramMinInterval){
            interval=histogramMinInterval;
        }else {
            interval=(width-initHistogramWidth*size)/(size+1);
        }
        rects.clear();
        for (int i = 0; i <size ; i++) {
            Rect targetRect = new Rect((initHistogramWidth+interval)*i+interval, altitude.get(i).intValue(),(initHistogramWidth+interval)*(i+1), height-initTextNameInterval);
            rects.add(targetRect);
        }
    }

    /**
     * 获取字体大小
     * @return  字体大小
     */
    public int getInitTextSize() {
        return initTextSize;
    }

    /**
     * 设置字体大小
     * @param initTextSize 字体大小
     */
    public void setInitTextSize(int initTextSize) {
        this.initTextSize = initTextSize;
        height=initHeight-initTextSize-initTextNameInterval;
        altitude=new ArrayList<Long>();
        int heightTop=height-initTextSize-initTextNumberInterval;
        for (int i = 0; i <size ; i++) {
            altitude.add(Math.round(height-(number.get(i)/numberTop*heightTop)));//转换比例高度
        }
        rects.clear();
        for (int i = 0; i <size ; i++) {
            Rect targetRect = new Rect((initHistogramWidth+interval)*i+interval, altitude.get(i).intValue(),(initHistogramWidth+interval)*(i+1), height-initTextNameInterval);
            rects.add(targetRect);
        }
    }

    /**
     * 获取名称间隔
     * @return   名称间隔
     */
    public int getInitTextNameInterval() {
        return initTextNameInterval;
    }

    /**
     * 设置名称间隔
     * @param initTextNameInterval 名称间隔
     */
    public void setInitTextNameInterval(int initTextNameInterval) {
        this.initTextNameInterval = initTextNameInterval;
        height=initHeight-initTextSize-initTextNameInterval;
        altitude=new ArrayList<Long>();
        int heightTop=height-initTextSize-initTextNumberInterval;
        for (int i = 0; i <size ; i++) {
            altitude.add(Math.round(height-(number.get(i)/numberTop*heightTop)));//转换比例高度
        }
        rects.clear();
        for (int i = 0; i <size ; i++) {
            Rect targetRect = new Rect((initHistogramWidth+interval)*i+interval, altitude.get(i).intValue(),(initHistogramWidth+interval)*(i+1), height-initTextNameInterval);
            rects.add(targetRect);
        }
    }

    /**
     * 获取数值距离柱状图间隔
     * @return  数值距离柱状图间隔
     */
    public int getInitTextNumberInterval() {
        return initTextNumberInterval;
    }

    /**
     * 设置数值距离柱状图间隔
     * @param initTextNumberInterval  数值距离柱状图间隔
     */
    public void setInitTextNumberInterval(int initTextNumberInterval) {
        this.initTextNumberInterval = initTextNumberInterval;
        height=initHeight-initTextSize-initTextNameInterval;
        altitude=new ArrayList<Long>();
        int heightTop=height-initTextSize-initTextNumberInterval;
        for (int i = 0; i <size ; i++) {
            altitude.add(Math.round(height-(number.get(i)/numberTop*heightTop)));//转换比例高度
        }
        rects.clear();
        for (int i = 0; i <size ; i++) {
            Rect targetRect = new Rect((initHistogramWidth+interval)*i+interval, altitude.get(i).intValue(),(initHistogramWidth+interval)*(i+1), height-initTextNameInterval);
            rects.add(targetRect);
        }
    }

    /**
     * 获取名称颜色
     * @return  名称颜色
     */
    public int getInitTextNameColor() {
        return initTextNameColor;
    }

    /**
     * 设置名称颜色
     * @param initTextNameColor  名称颜色
     */
    public void setInitTextNameColor(int initTextNameColor) {
        this.initTextNameColor = initTextNameColor;
    }

    /**
     * 获取数值颜色
     * @return  数值颜色
     */
    public int getInitTextNumberColor() {
        return initTextNumberColor;
    }

    /**
     * 设置数值颜色
     * @param initTextNumberColor  数值颜色
     */
    public void setInitTextNumberColor(int initTextNumberColor) {
        this.initTextNumberColor = initTextNumberColor;
    }

    /**
     * 获取默认字体颜色
     * @return   默认字体颜色
     */
    public Boolean getInitTextColor() {
        return initTextColor;
    }

    /**
     * 设置字体颜色
     * @param initTextColor  字体颜色
     */
    public void setInitTextColor(Boolean initTextColor) {
        this.initTextColor = initTextColor;
    }

    private int dp(float value) {
        if (value == 0) {
            return 0;
        }
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) Math.ceil(density * value);
    }


    int size=1;//柱状图数量
    ArrayList<String> name;   //名称
    ArrayList<Double> number; //数值
    ArrayList<Long> altitude; //柱状图高度
    double numberTop;         //数值最大值

    /**
     * 赋值
     * @param name     名称
     * @param number   数值
     */
    public void setPaints ( ArrayList<String> name, ArrayList<Double> number){
    size=name.size();
    this.name=name;
    this.number=number;
     numberTop=number.get(0);//获取第一个值当最大值
    for (int i = 1; i <size ; i++) {//遍历集合
//        if (number.get(i)>numberTop){//进行判断获取最大值
//            numberTop=number.get(i);//获取最大值
//        }
        numberTop=Math.max(number.get(i),numberTop);//获取最大值
    }
    altitude=new ArrayList<Long>();
        int heightTop=height-initTextSize-initTextNumberInterval;
    for (int i = 0; i <size ; i++) {
altitude.add(Math.round(height-(number.get(i)/numberTop*heightTop)));//转换比例高度
    }
    paints.clear();
    rects.clear();
    int intervals=(width-initHistogramWidth*size)/(size+1);
    if (intervals<histogramMinInterval){
        interval=histogramMinInterval;
    }else {
interval=(width-initHistogramWidth*size)/(size+1);
    }
    for (int i = 0; i <size ; i++) {
        Paint paint=new Paint();
        paints.add(paint);
        paints.get(i).setColor(colours.get(0));
        //histogramWidth*(i+1)+interval*(i+1)
        //histogramWidth*i+interval*(i+1)
        Rect targetRect = new Rect((initHistogramWidth+interval)*i+interval, altitude.get(i).intValue(),(initHistogramWidth+interval)*(i+1), height-initTextNameInterval);
        rects.add(targetRect);
    }
//    Rect targetRect = new Rect(10, 100,110, canvass.getHeight());
//    Rect targetRect1 = new Rect(130, 200,240, canvass.getHeight());
//    Rect targetRect2 = new Rect(260, 300,370, canvass.getHeight());
//    Rect targetRect3 = new Rect(390, 400,500, canvass.getHeight());
//    rects.add(targetRect);
//    rects.add(targetRect1);
//    rects.add(targetRect2);
//    rects.add(targetRect3);
    postInvalidate();
    initMove();
}
int dx;//x轴滑动距离
int dy;//y轴滑动距离

    /**
     * 滑动
     * @param x    x轴滑动距离
     * @param y    y轴滑动距离（摆设  用不到）
     */
    public void setMove(int x,int y){
     dx+=x;
     dy+=y;
    rects.clear();
    int intervals=(width-initHistogramWidth*size)/(size+1);
    if (intervals<histogramMinInterval){
        interval=histogramMinInterval;
    }else {
        interval=(width-initHistogramWidth*size)/(size+1);
    }
    for (int i = 0; i <size ; i++) {
        Rect targetRect = new Rect((initHistogramWidth+interval)*i+interval-dx, altitude.get(i).intValue(),(initHistogramWidth+interval)*(i+1)-dx, height-initTextNameInterval);
        rects.add(targetRect);
    }
    postInvalidate();
}

    /**
     * 设置颜色
     * @param colours      颜色
     */
    public void setColors(ArrayList<String> colours){
    this.colours=new ArrayList<Integer>();
    for (int i = 0; i <colours.size() ; i++) {
        this.colours.add(Color.parseColor(colours.get(i)));
    }
}

    /**
     * 初始话移动的距离
     */
    public void initMove(){
    dx=0;
    dy=0;
}
    //上一次触摸行为的x坐标
    private int mLastX;
    //上一次触摸行为的y坐标
    private int mLastY;

    /**
     * 触摸事件
     * @param event     触摸
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                int dx = mLastX - x;
                int dy = mLastY - y;
                if (scrollView!=null){
                    scrollView.requestDisallowInterceptTouchEvent(true);
                }
                if ((initHistogramWidth+interval)*size+interval-(this.dx+dx) >width&&interval-(this.dx+dx)<interval){
                setMove(dx, dy);
                }else {

                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        if ((initHistogramWidth+interval)*size+interval-this.dx >width){
        mLastX = x;
        mLastY = y;
        }
        return super.onTouchEvent(event);
    }
    ScrollView scrollView;

    /**
     * ScrollView  解决ScrollView冲突问题  （暂时只是这个（柱状图一半只会碰到这种情况，不可能放到listiew里面,ViewPager自己处理(可以放到viewpager触摸事件,没试过不保证)））可以直接把参数替换成自己冲突的控件
     * @param scrollView 冲突的控件
     */
    public void solveConflict(ScrollView scrollView){
        this.scrollView=scrollView;
}
}
