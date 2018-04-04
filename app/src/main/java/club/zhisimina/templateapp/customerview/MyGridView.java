package club.zhisimina.templateapp.customerview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * @description  修改grildview高度
 * @author  hankehui
 * @date  2018/2/27 15:20
 */

public class MyGridView extends GridView{
    public MyGridView(Context context) {
        super(context);
    }

    public MyGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec=MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>>2,MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
