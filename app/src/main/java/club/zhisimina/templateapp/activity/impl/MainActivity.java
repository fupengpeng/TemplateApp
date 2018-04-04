package club.zhisimina.templateapp.activity.impl;

import android.os.Bundle;

import club.zhisimina.templateapp.R;
import club.zhisimina.templateapp.activity.view.IMainView;

/**
 * 主界面
 */
public class MainActivity extends BaseActivity implements IMainView {





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.activity_main);


    }


    @Override
    public void showMenu() {

    }

    @Override
    public void closeMenu() {

    }

    @Override
    public void alterPasswordSuccess(String data) {

    }

    @Override
    public void onAlterPasswordFail(Exception e) {

    }
}
