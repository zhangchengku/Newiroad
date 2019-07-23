package com.zggk.newiroad.main;

import android.content.Context;

import com.zggk.newiroad.Bean.LiXianDatabean;
import com.zggk.newiroad.mvp.BasePresenter;
import com.zggk.newiroad.mvp.BaseRequestView;
import com.zggk.newiroad.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class MainContract {
    interface View extends BaseRequestView {

        void getupdatedb(LiXianDatabean liXianDatabean);


    }

    interface Presenter extends BasePresenter<View> {
        void updatedb(String userid,String gydwid);
    }
}

