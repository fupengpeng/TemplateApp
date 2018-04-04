package club.zhisimina.templateapp.presenter.factory;

import club.zhisimina.templateapp.activity.view.IMainView;
import club.zhisimina.templateapp.presenter.impl.MainViewPresenter;
import club.zhisimina.templateapp.presenter.interf.IMainViewPresenter;

/**
 * @author fupengpeng
 * @description 主界面主导器工厂
 * @data 2018/1/17 0017 18:19
 */

public class MainViewPresenterFactory {

    /**
     * 创建主界面主导器对象
     *
     * @param mainView 主界面
     * @return 主界面主导器对象
     */
    public static IMainViewPresenter newInstance(IMainView mainView) {
        return new MainViewPresenter(mainView);
    }


}
