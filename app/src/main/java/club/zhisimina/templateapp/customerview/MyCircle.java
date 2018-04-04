package club.zhisimina.templateapp.customerview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import org.greenrobot.eventbus.EventBus;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

import club.zhisimina.templateapp.common.DataEvent;

/**
 * Created by hankehui on 2017/12/16.
 * 圆盘显示数据
 */

public class MyCircle extends View{
    Paint mPaint;
    Paint mPaint1;
    Paint mPaint2;
    Paint mPaint3;
    Paint mPaintCircle;
    Paint mPaintCircle1;
    Paint mPaintTxt;
    Paint mPaintTxt1;
    int mHeight;
    int mWidth;
    RectF mRectF;
    int mState = 1;
    int mStart = -90;
    int mEnd;
    int end1;
    int end2;
    int end3;
    //text1颜色
    int click1=Color.parseColor("#000000");
    //text颜色
    int click=Color.parseColor("#b3b3b3");
    //透明度
    int diaphaneity=90;
public static final int ALL=360;
int speed =500;
    public MyCircle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint1 = new Paint();
        mPaint2 = new Paint();
        mPaint3 = new Paint();
        mPaintCircle=new Paint();
        mPaintCircle1=new Paint();
        mPaintTxt=new Paint();
        mPaintTxt1=new Paint();
    }

    public MyCircle(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyCircle(Context context) {
        this(context, null);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        // canvas.drawCircle(400, 400, 200, mPaint);
        // canvas.drawText("Hello world!", 200, 300, mPaint);
        super.onDraw(canvas);
        mPaint.setColor(Color.parseColor("#0000FF"));
        // 抗锯齿
        mPaint.setAntiAlias(true);
        mPaint1.setColor(Color.parseColor("#00FF00"));
        // 抗锯齿
        mPaint1.setAntiAlias(true);
        mPaint2.setColor(Color.parseColor("#FF8000"));
        // 抗锯齿
        mPaint2.setAntiAlias(true);
        mPaint3.setColor(Color.parseColor("#FF0000"));
        // 抗锯齿
        mPaint3.setAntiAlias(true);
        mPaintCircle.setColor(Color.parseColor("#FFFFFF"));
        mPaintCircle.setAntiAlias(true);
        mPaintCircle1.setColor(Color.parseColor("#FFFFFF"));
        mPaintCircle1.setAntiAlias(true);
        mPaintCircle1.setAlpha(diaphaneity);
        mPaintTxt.setColor(click);
        mPaintTxt.setAntiAlias(true);
        mPaintTxt.setTextSize(35);
        mPaintTxt.setStrokeWidth(3);//画笔的宽度
        Paint.FontMetricsInt fontMetrics = mPaintTxt.getFontMetricsInt();
        Rect targetRect = new Rect(0, 0, mWidth, mHeight);
        int baseline = (targetRect.bottom + targetRect.top - fontMetrics.bottom - fontMetrics.top) / 2;
        mPaintTxt.setTextAlign(Paint.Align.CENTER);

        mPaintTxt1.setColor(click1);
        mPaintTxt1.setAntiAlias(true);
        mPaintTxt1.setTextSize(35);
        mPaintTxt1.setStrokeWidth(3);//画笔的宽度
        Paint.FontMetricsInt fontMetrics1 = mPaintTxt1.getFontMetricsInt();
        int baseline1 = (targetRect.bottom + targetRect.top - fontMetrics1.bottom - fontMetrics1.top) / 2;
        mPaintTxt1.setTextAlign(Paint.Align.CENTER);
        canvas.drawArc(mRectF, mStart+mEnd+end1+end2, end3, true, mPaint3);
        canvas.drawArc(mRectF, mStart+mEnd+end1, end2, true, mPaint2);
        canvas.drawArc(mRectF, mStart+mEnd, end1, true, mPaint1);
        canvas.drawArc(mRectF, mStart, mEnd, true, mPaint);
        canvas.drawCircle( mWidth/2, mHeight/2, 200,mPaintCircle1);
        canvas.drawCircle( mWidth/2, mHeight/2, 180,mPaintCircle);
        canvas.drawText("按销售类型", targetRect.centerX(), baseline-80, mPaintTxt);
        canvas.drawText("按支付方式", targetRect.centerX(), baseline1+80, mPaintTxt1);
        // canvas.drawText( "按销售类型",mWidth/2, mHeight/2, mPaintTxt);
    }

    // 测量父空间大小
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // TODO Auto-generated method stub
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // measure(widthMeasureSpec, heightMeasureSpec);
        // widthMeasureSpec 父空间的宽 heightMeasureSpec 父空间的高
        // getMeasuredHeight();
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
        mRectF = new RectF(0, 0, mWidth, mHeight);

    }

    // 计时器 前进 后退 动态改变

    Boolean mIsRuning = false;

    double s1;
    double s2;
    double s3;
    double s4;
    int sf1;
    int sf2;
    int sf3;
    int sf4;

    /**
     *    圆盘数据  百分比显示
     * @param all               总
     * @param sector1           第一个切块
     * @param sector2           第二个切块
     * @param sector3           第三个切块
     * @param sector4           第四个切块
     * @param income_approach   方式
     */
    public void setSweegle(final double all,final double  sector1,final double  sector2,final double  sector3,final double  sector4 ,boolean income_approach) {
        if (!income_approach){
            click=Color.parseColor("#000000");
            click1=Color.parseColor("#b3b3b3");
            Log.e("------------",income_approach+"");
        }else {
            click=Color.parseColor("#b3b3b3");
            click1=Color.parseColor("#000000");
            Log.e("---true---------",income_approach+"");
        }
s1=sector1/all;
        s2=sector2/all;
        s3=sector3/all;
        s4=sector4/all;
        time();
        DecimalFormat df = new DecimalFormat("###0.0");
        double se=ALL*s1;
        sf1= Integer.parseInt(String.valueOf(new BigDecimal(df.format(se)).setScale(0,BigDecimal.ROUND_HALF_UP)));
        double se2=ALL*s2;
        sf2= Integer.parseInt(String.valueOf(new BigDecimal(df.format(se2)).setScale(0,BigDecimal.ROUND_HALF_UP)));
        double se3=ALL*s3;
        sf3= Integer.parseInt(String.valueOf(new BigDecimal(df.format(se3)).setScale(0,BigDecimal.ROUND_HALF_UP)));
        double se4=ALL*s4;

        sf4= Integer.parseInt(String.valueOf(new BigDecimal(df.format(se4)).setScale(0,BigDecimal.ROUND_HALF_UP)));
        mEnd=0;
        mStart=-90;
        time1();
        time2();
        time3();
        time4();
        diaphaneity=0;

        setDiaphaneity();
        double percent1=s1*100;
        double percent2=s2*100;
        double percent3=s3*100;
        double percent4=s4*100;
        DecimalFormat dff = new DecimalFormat("###0.00");
        double []percent={Double.parseDouble(dff.format(percent1)),Double.parseDouble(dff.format(percent2)),Double.parseDouble(dff.format(percent3)),Double.parseDouble(dff.format(percent4))};
        // TODO: 2018/4/3 0003   1001????????
        EventBus.getDefault().post(new DataEvent(1001,percent));
    }
    private void setDiaphaneity(){
        final Timer timer = new Timer();// 计时器
        TimerTask taskf = new TimerTask() {
            // 计时执行者
            @Override
            public void run() {
                // TODO Auto-generated method stub
                switch (mState) {
                    case 1:
                        diaphaneity++;
                        postInvalidate();
                        if (diaphaneity>90){
                            diaphaneity=90;
                            timer.cancel();
                        }
                        break;
                }
            }
        };
int time =speed/80;
        timer.scheduleAtFixedRate(taskf, 0, time);// 启动计时器
    }
    private void time1(){
        final Timer timerf = new Timer();// 计时器
        TimerTask taskf = new TimerTask() {
            // 计时执行者
            @Override
            public void run() {
                // TODO Auto-generated method stub
                switch (mState) {
                    case 1:
                        mEnd++;
                        postInvalidate();
                        if (mEnd>=sf1){
                            mEnd=sf1;
                            timerf.cancel();
                        }
                        break;
                }
            }
        };
        double sect1=1-s1;
        double sect=speed/sf1;
        int ss=Integer.parseInt(new DecimalFormat("0").format(sect));
        timerf.scheduleAtFixedRate(taskf, 0, ss);// 启动计时器
    }
    private void time2(){
        final Timer timer2 = new Timer();// 计时器
        TimerTask task2 = new TimerTask() {
            // 计时执行者
            @Override
            public void run() {
                // TODO Auto-generated method stub
                switch (mState) {
                    case 1:
                        end1++;
                        postInvalidate();
                        if (end1>=sf2){
                            end1=sf2;
                            timer2.cancel();
                        }
                        break;
                }
            }
        };
        double sect2=1-s2;
        double sect22=speed/sf2;
        int ss2=Integer.parseInt(new DecimalFormat("0").format(sect22));
        timer2.scheduleAtFixedRate(task2, 0, ss2);// 启动计时器
    }
    private void time3() {
            final Timer timer3 = new Timer();// 计时器
            TimerTask task3 = new TimerTask() {
                // 计时执行者
                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    switch (mState) {
                        case 1:
                            end2++;
                            postInvalidate();
                            if (end2 >= sf3) {
                                end2 = sf3;
                                timer3.cancel();
                            }
                            break;
                    }
                }
            };
            double sect3 = 1 - s3;
            double sect33 = speed / sf3;
            int ss3 = Integer.parseInt(new DecimalFormat("0").format(sect33));
            timer3.scheduleAtFixedRate(task3, 0, ss3);// 启动计时器
        }


    private void time4(){
        final Timer timer4 = new Timer();// 计时器
        TimerTask task4 = new TimerTask() {
            // 计时执行者
            @Override
            public void run() {
                // TODO Auto-generated method stub
                switch (mState) {
                    case 1:
                        end3++;
                        postInvalidate();
                        if (end3>=sf4){
                            end3=sf4;
                            timer4.cancel();
                        }
                        break;
                }
            }
        };
      //  double sect4=1-s4;
        double sect44=speed/sf4;
        int ss4=Integer.parseInt(new DecimalFormat("0").format(sect44));
        timer4.scheduleAtFixedRate(task4, 0, ss4);// 启动计时器
    }
    private void time(){
    }
}

