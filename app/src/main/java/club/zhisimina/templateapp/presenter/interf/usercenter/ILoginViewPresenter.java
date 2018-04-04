package club.zhisimina.templateapp.presenter.interf.usercenter;

/**
 * 登录界面主导器
 */
public interface ILoginViewPresenter {
    /**
     * 登录
     * @param phoneNumber 手机号
     * @param password 密码
     */
    void login(String phoneNumber, String password);
}
