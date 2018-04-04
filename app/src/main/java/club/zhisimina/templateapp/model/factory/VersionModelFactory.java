package club.zhisimina.templateapp.model.factory;

import club.zhisimina.templateapp.model.impl.VersionModel;
import club.zhisimina.templateapp.model.interf.IVersionModel;

/**
 * 版本业务工厂
 */
public class VersionModelFactory {

    /**
     * 创建版本业务实例
     *
     * @return 版本业务实例
     */
    public static IVersionModel newInstance() {
        return new VersionModel();
    }
}
