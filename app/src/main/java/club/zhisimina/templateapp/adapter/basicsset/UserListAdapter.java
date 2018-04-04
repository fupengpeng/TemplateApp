package club.zhisimina.templateapp.adapter.basicsset;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import club.zhisimina.templateapp.adapter.BaseAdapter;
import club.zhisimina.templateapp.entity.User;

/**
 * @author fupengpeng
 * @description 店员列表适配器
 * @date 2018/3/7 0007 14:52
 */
public class UserListAdapter extends BaseAdapter<User> {



    /**
     * 构造方法
     *
     * @param context Context对象，值不能为null
     * @param data
     */
    public UserListAdapter(Context context, List<User> data) {
        super(context, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
