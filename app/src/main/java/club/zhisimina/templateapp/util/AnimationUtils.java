package club.zhisimina.templateapp.util;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.util.Log;
import android.view.View;

/**
 * Created by hankehui on 2017/12/19.
 * 动画工具类
 */

public class AnimationUtils {
    /**
     * 旋转动画
     * @param view          控件
     * @param start         起始点
     * @param end           结束点
     * @param time          时间
     */
    public static void startPropertyAnim(View view, float start, float end, int time)
    {
        ObjectAnimator anim = ObjectAnimator.ofFloat(view, "rotation", start, end);
        anim.setDuration(time);
        // 回调监听
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (Float) animation.getAnimatedValue();
                Log.d("zhangphil", value + "");
            }
        });
        // 正式开始启动执行动画
        anim.start();
    }
    /**
     * 旋转动画
     * @param view          控件
     * @param start         起始点
     * @param end           结束点
     */
    public static void startPropertyAnim(View view, float start, float end)
    {
        ObjectAnimator anim = ObjectAnimator.ofFloat(view, "rotation", start, end);
        anim.setDuration(300);
        // 回调监听
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (Float) animation.getAnimatedValue();
                Log.d("zhangphil", value + "");
            }
        });
        // 正式开始启动执行动画
        anim.start();
    }
}
