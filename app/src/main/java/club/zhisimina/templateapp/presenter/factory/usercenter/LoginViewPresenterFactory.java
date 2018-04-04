package club.zhisimina.templateapp.presenter.factory.usercenter;

import club.zhisimina.templateapp.activity.view.usercenter.ILoginView;
import club.zhisimina.templateapp.presenter.impl.usercenter.LoginViewPresenter;
import club.zhisimina.templateapp.presenter.interf.usercenter.ILoginViewPresenter;

/**
 * 登录界面主导器工厂
 */
public class LoginViewPresenterFactory {
    /**
     * 创建登录界面主导器对象
     *
     * @param loginView 登录界面
     * @return 登录界面主导器对象
     */
    public static ILoginViewPresenter newInstance(ILoginView loginView) {
        return new LoginViewPresenter(loginView);
    }

}
