package club.zhisimina.templateapp.fragment.impl;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import club.zhisimina.templateapp.util.DialogUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Fragment基类
 */
public class BaseFragment extends Fragment {
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
     * 当界面创建时
     *
     * @param inflater           布局加载器
     * @param container          容器
     * @param savedInstanceState Bundle
     * @param layoutResId        布局资源Id
     * @return 界面View
     */
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState, int layoutResId) {
        View view = inflater.inflate(layoutResId, container, false);
        // ButterKnife绑定
        unbinder = ButterKnife.bind(this, view);

        return view;
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
    /**
     * 显示Fragment
     *
     * @param containerId
     * @param oldFragment
     * @param newFragment
     */
    public void showFragment(int containerId, BaseFragment oldFragment, BaseFragment newFragment) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
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
        getActivity().startActivity(new Intent(getActivity(), cls));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // 解除ButterKnife绑定
        if (unbinder != null) {
            unbinder.unbind();
        }
    }
}
