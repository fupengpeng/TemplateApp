package club.zhisimina.templateapp.model.factory;

import club.zhisimina.templateapp.model.impl.SetModel;
import club.zhisimina.templateapp.model.interf.ISetModel;

/**
 * @author fupengpeng
 * @description 设置业务工厂
 * @data 2018/1/29 0029 14:00
 */

public class SetModelFactory {

    /**
     * 创建设置业务实例
     *
     * @return 设置业务实例
     */
    public static ISetModel newInstance() {
        return new SetModel();
    }

}
