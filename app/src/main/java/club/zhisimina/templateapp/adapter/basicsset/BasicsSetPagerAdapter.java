package com.jdlx.cloudgatherbeautiful.adapter.basicsset;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * @description  设置界面viewpager适配器
 * @author  fupengpeng
 * @date  2018/1/17 0017 13:09
 */

public class BasicsSetPagerAdapter extends FragmentPagerAdapter {

    private List<String> title;
    private List<Fragment> views;

    public BasicsSetPagerAdapter(FragmentManager fragmentManager, List<String> title, List<Fragment> views) {
        super(fragmentManager);
        this.title = title;
        this.views = views;
    }

    @Override
    public Fragment getItem(int position) {
        return views.get(position);
    }

    @Override
    public int getCount() {
        return views.size();
    }


    //配置标题的方法
    @Override
    public CharSequence getPageTitle(int position) {
        return title.get(position);
    }


}


