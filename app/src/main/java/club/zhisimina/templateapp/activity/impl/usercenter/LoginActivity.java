package club.zhisimina.templateapp.activity.impl.usercenter;

import android.content.Intent;
import android.os.Bundle;

import org.greenrobot.eventbus.EventBus;

import butterknife.OnClick;
import club.zhisimina.templateapp.R;
import club.zhisimina.templateapp.activity.view.usercenter.ILoginView;
import club.zhisimina.templateapp.service.VersionUpdateService;
import club.zhisimina.templateapp.tools.BaseActivity;
import club.zhisimina.templateapp.util.InfoUtils;

/**
 * 登录界面
 */
public class LoginActivity extends BaseActivity implements ILoginView {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.activity_login);

        // 订阅
        EventBus.getDefault().register(this);
        // 启动检测版本服务
        startService(new Intent(this, VersionUpdateService.class));

    }


    /**
     * 登录
     */
    @OnClick(R.id.btn_login)
    public void login() {


    }





    /**
     * 当登录成功
     */
    @Override
    public void onLoginSuccess() {


    }

    /**
     * 当登录失败
     */
    @Override
    public void onLoginFail(Exception e) {
        // 关闭等待对话框
        closeWaitDialog();
        // 显示失败信息
        InfoUtils.showInfo(this, e.getMessage());
    }

    @Override
    protected void onDestroy() {
        // 解除订阅
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
