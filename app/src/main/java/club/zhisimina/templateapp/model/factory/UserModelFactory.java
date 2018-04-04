package club.zhisimina.templateapp.model.factory;

import club.zhisimina.templateapp.model.impl.UserModel;
import club.zhisimina.templateapp.model.interf.IUserModel;

/**
 * 用户业务工厂
 */
public class UserModelFactory {

    /**
     * 创建用户业务实例
     *
     * @return 用户业务实例
     */
    public static IUserModel newInstance() {
        return new UserModel();
    }
}
