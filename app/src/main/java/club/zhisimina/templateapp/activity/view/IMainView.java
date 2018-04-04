package club.zhisimina.templateapp.activity.view;


/**
 * 主界面
 */
public interface IMainView {
    /**
     * 打开侧滑菜单
     */
    void showMenu();
    /**
     * 关闭侧滑菜单
     */
    void closeMenu();

    /**
     * 更改密码成功
     *
     * @param data
     */
    void alterPasswordSuccess(String data);

    /**
     * 更改密码失败
     *
     * @param e
     */
    void onAlterPasswordFail(Exception e);

}
