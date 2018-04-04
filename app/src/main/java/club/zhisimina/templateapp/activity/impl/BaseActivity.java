package club.zhisimina.templateapp.activity.impl;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import java.lang.reflect.Field;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import club.zhisimina.templateapp.application.MyApplication;
import club.zhisimina.templateapp.fragment.impl.BaseFragment;
import club.zhisimina.templateapp.util.DialogUtils;


/**
 * Activity基类
 */
public class BaseActivity extends AppCompatActivity {
    /**
     * 用于解除ButterKnife绑定
     */
    private Unbinder unbinder;

    /**
     * 对话框工具类
     */
    public DialogUtils dialogUtils = null;
    /**
     * 网络请求时的等待对话框
     */
    private Dialog dialog = null;

    /**
     * 当Activity创建
     *
     * @param savedInstanceState Bundle
     * @param layoutResId        界面布局文件ID
     */
    protected void onCreate(Bundle savedInstanceState, int layoutResId) {
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            try {
                Class decorViewClazz = Class.forName("com.android.internal.policy.DecorView");
                Field field = decorViewClazz.getDeclaredField("mSemiTransparentStatusBarColor");
                field.setAccessible(true);
                field.setInt(getWindow().getDecorView(), Color.TRANSPARENT);  //改为透明
            } catch (Exception e) {}
        }
        setContentView(layoutResId);

        // ButterKnife绑定
        unbinder = ButterKnife.bind(this);

        // 创建对话框工具类
        dialogUtils = new DialogUtils(this);

        // 沉浸式
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        // 保存Activity
        MyApplication.getInstance().addActivity(this);
    }

    /**
     * 显示Fragment
     *
     * @param containerId
     * @param oldFragment
     * @param newFragment
     */
    public void showFragment(int containerId, BaseFragment oldFragment, BaseFragment newFragment) {
        FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction();
        if (!newFragment.isAdded()) {
            transaction.add(containerId, newFragment);
        }
        if (oldFragment != null) {
            transaction.hide(oldFragment);
        }
        transaction.show(newFragment);
        transaction.commitAllowingStateLoss();
    }

    /**
     * 打开Activity
     *
     * @param cls 目标class
     */
    public void startActivity(Class<?> cls) {
        startActivity(new Intent(this, cls));
    }

    /**
     * 显示等待对话框
     *
     * @param message 提示信息
     */
    public void showWaitDialog(String message) {
        if (dialogUtils != null && (dialog == null || !dialog.isShowing())) {
            dialog = dialogUtils.showLoading(message);
        }
    }

    /**
     * 关闭等待对话框
     */
    public void closeWaitDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
        // 移除保存的Activity
        MyApplication.getInstance().removeActivity(this);
        super.onDestroy();
        // 解除ButterKnife绑定
        if (unbinder != null) {
            unbinder.unbind();
        }
    }
}
