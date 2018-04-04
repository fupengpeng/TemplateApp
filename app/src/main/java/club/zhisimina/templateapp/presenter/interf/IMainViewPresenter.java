package club.zhisimina.templateapp.presenter.interf;

/**
 * @author fupengpeng
 * @description 主界面主导器
 * @data 2018/1/17 0017 18:17
 */

public interface IMainViewPresenter {

    /**
     * 更改密码
     *
     * @param newpwd  新密码（大于6位）
     * @param opwd    原密码
     */
    void alterPassword(String newpwd , String opwd);

}
