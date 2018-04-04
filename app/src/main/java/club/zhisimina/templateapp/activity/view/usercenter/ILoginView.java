package club.zhisimina.templateapp.activity.view.usercenter;

/**
 * 登录界面
 */
public interface ILoginView {
    /**
     * 当登录成功
     */
    void onLoginSuccess();
    /**
     * 当登录失败
     */
    void onLoginFail(Exception e);
}
